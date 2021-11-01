package com.example.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter
@Setter
@Entity
@Table(name = "transacao")
public class Transacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4937435397910368171L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(optional = true)
	private Conta conta;
	
	@Column(nullable = false)
	private double valor;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dataTransacao")
	private Date dataTransacao;
}
