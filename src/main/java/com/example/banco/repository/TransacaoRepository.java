package com.example.banco.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.banco.model.Conta;
import com.example.banco.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	List<Transacao> findByConta(Optional<Conta> c);
	
	//List<Transacao> findByContaAndDate(Conta conta, Date dataIni, Date dataFim);
	
	@Query(value = "from Transacao t where dataTransacao BETWEEN :dataIni AND :dataFim")
	public List<Transacao> getAllBetweenDates(@Param("dataIni")Date dataIni,@Param("dataFim")Date dataFim);
}
