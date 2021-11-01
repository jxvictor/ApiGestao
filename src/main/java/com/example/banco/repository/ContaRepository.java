package com.example.banco.repository;

//import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.banco.model.Conta;


public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	Conta findById(long id);
	
	@Modifying
	@Transactional
	@Query
	("UPDATE Conta c SET c.flagAtivo = false WHERE c.id = ?2")
	long blockById(long id);
	
	@Modifying
	@Transactional
	@Query("update Conta c set c.saldo = c.saldo + ?1 where c.id = ?2")
	void setSaldoNewTo(double quantidade, Long id);
	
	@Modifying
	@Transactional
	@Query("update Conta c set c.saldo = c.saldo - ?1 where c.id = ?2")
	void setSaldoNew(double quantidade, Long id);
	
	
	@Query("SELECT saldo FROM Conta WHERE id = ?1") 
    Conta findSaldoById(long id);	
	
}
