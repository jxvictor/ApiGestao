package com.example.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.model.Pessoa;
import com.example.banco.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	PessoaRepository pessoaRepository;
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> findById(Long id){
		return pessoaRepository.findById(id);
	}
	
	public Pessoa save(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
	
	public void delete(Pessoa pessoa){
		pessoaRepository.delete(pessoa);
	}
}
