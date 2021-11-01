package com.example.banco.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.model.Conta;
import com.example.banco.model.Transacao;
import com.example.banco.repository.ContaRepository;


@Service
public class ContaService {
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TransacaoService transacaoService;
	
	public List<Conta> findAll(){
		return contaRepository.findAll();
	}
	
	public Optional<Conta> findById(Long id){
		return contaRepository.findById(id);
	}
	
	public Conta save(Conta conta){
        return contaRepository.save(conta);
    }
	
	public void depositar(double quantidade, Long id) {
		contaRepository.setSaldoNewTo(quantidade, id);
	}
	
	public void sacar(double quantidade, Long id) {
		contaRepository.setSaldoNew(quantidade, id);
	}
	
	public void bloquear(Long id, Boolean flagAtivo) {
		contaRepository.blockById(id, flagAtivo);
	}
	
	public void delete(Conta conta){
		contaRepository.delete(conta);
	}
	
	public Optional<Conta> verificarConta(Long id) {
		return contaRepository.findById(id);
	}
	
	public List<Transacao> extrato(Long id) {
		Optional<Conta> c = verificarConta(id);
		return transacaoService.buscarConta(c);
		
    }
	

}
