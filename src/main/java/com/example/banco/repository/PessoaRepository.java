package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banco.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
