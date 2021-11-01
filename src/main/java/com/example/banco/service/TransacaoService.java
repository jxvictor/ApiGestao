package com.example.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.model.Conta;
import com.example.banco.model.Transacao;
import com.example.banco.repository.TransacaoRepository;

@Service
public class TransacaoService {
	@Autowired
	TransacaoRepository transacaoRepository;
	
	public List<Transacao> findAll(){
		return transacaoRepository.findAll();
	}
	
	public Optional<Transacao> findById(Long id){
		return transacaoRepository.findById(id);
	}
	
	public Transacao save(Transacao transacao){
        return transacaoRepository.save(transacao);
    }
	
	public void delete(Transacao transacao){
		transacaoRepository.delete(transacao);
	}
	
	public List<Transacao> buscarConta(Optional<Conta> c) {
		return transacaoRepository.findByConta(c);
	}
}
