package com.example.banco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "Identificador único da transação")
	private long id;
	
	@ManyToOne(optional = true)
	@ApiModelProperty(value = "Identificador único da Conta")
	private Conta conta;
	
	@Column(nullable = false)
	@ApiModelProperty(value = "Digite o valor da transação")
	private double valor;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dataTransacao")
	@ApiModelProperty(value = "Digite a data da transação")
	private Date dataTransacao;
}
