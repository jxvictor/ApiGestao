package com.example.banco.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.model.Transacao;
import com.example.banco.service.TransacaoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	
	@Autowired
	TransacaoService transacaoService;
	
	@ApiOperation(value = "Retorna todas as transações")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@GetMapping()
	public ResponseEntity<List<Transacao>> obterTodos(){		
		try
		{
			return new ResponseEntity<List<Transacao>>(transacaoService.findAll(), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<List<Transacao>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cadastra uma transação nova")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 201, message = "Criado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Endpoint não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@PostMapping()
	public ResponseEntity<String> cadastrar(@RequestBody @Valid Transacao transacao)
	{
		try
		{
			transacaoService.save(transacao);
			return new ResponseEntity<String>("Transacao cadastrada com sucesso", HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}