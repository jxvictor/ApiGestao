package com.example.banco.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.model.Conta;
import com.example.banco.model.Transacao;
import com.example.banco.repository.ContaRepository;
import com.example.banco.repository.TransacaoRepository;
import com.example.banco.service.ContaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/conta", produces = "application/json")
public class ContaController {
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TransacaoRepository transacaoRepository;
	

	@ApiOperation(value = "Retorna todas as contas cadastrados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping()
	public ResponseEntity<List<Conta>> obterTodos(){		
		try
		{
			return new ResponseEntity<List<Conta>>(contaService.findAll(), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<List<Conta>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cadastra uma conta nova")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PostMapping()
	public ResponseEntity<String> cadastrar(@RequestBody @Valid Conta conta)
	{
		try
		{
			conta.setDataCriacao(new Date());
			contaService.save(conta);
			return new ResponseEntity<String>("Conta cadastrada com sucesso", HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Deposita uma quantidade de dinheiro para um conta especificando o id da conta.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PutMapping("/depositar/{quantidade}/{id}")
	public ResponseEntity<String> depositar(@PathVariable double quantidade, @PathVariable Long id){
		try {
			this.contaService.depositar(quantidade, id);
			return new ResponseEntity<String>("Depósito realizado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Saca uma quantidade de dinheiro de uma conta especificada por uma id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PutMapping("/sacar/{quantidade}/{id}")
	public ResponseEntity<String> sacar(@PathVariable double quantidade, @PathVariable Long id) throws Exception{
		try {
			if (quantidade <= 0) {
				throw new Exception("valor incorreto");
			}
			this.contaService.sacar(quantidade, id);
			return new ResponseEntity<String>("Saque realizado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Bloqueia uma conta especificando o id e mudando o flagAtivo para false")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PutMapping("/bloquear/{id}/{flagAtivo}")
	public ResponseEntity<String> bloquear(@PathVariable Long id, @PathVariable Boolean flagAtivo) {
		try
		{
			this.contaService.bloquear(id, flagAtivo);
			return new ResponseEntity<String>("Conta bloqueada!", HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna o saldo de uma conta especificada por uma id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/saldo/{id}")
    public ResponseEntity<Double> saldo(@PathVariable(value="id") long id){
        Optional<Conta> conta0 = contaService.findById(id);
        if(!conta0.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<Double>(conta0.get().getSaldo(), HttpStatus.OK);
        }
    }
	
	@ApiOperation(value = "Edita uma conta especificada por uma id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PutMapping("/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable(value="id") Long id, @RequestBody @Valid Conta conta) {
        Optional<Conta> c = contaService.findById(id);
        try
		{
        	conta.setId(c.get().getId());
            return new ResponseEntity<Conta>(contaService.save(conta), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Conta>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Retorna o extrato de transações de uma conta especificada por uma id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/extrato/{id}")
	public ResponseEntity<?> extrato(@PathVariable Long id){
		List<Transacao> transacoes = contaService.extrato(id);
		return new ResponseEntity<>(transacoes, HttpStatus.OK);   
	}
	
	@ApiOperation(value = "Retorna o extrato de transações por período")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping("/extrato/periodo")
	public List<Transacao> extratoPeriodo(@RequestParam Date dataIni, Date dataFim) {
		return transacaoRepository.getAllBetweenDates(dataIni, dataFim);
	}
	
}
