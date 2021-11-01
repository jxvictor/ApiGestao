package com.example.banco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6258925044907066150L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column()
	@ApiModelProperty(value = "Identificador único para a Conta")
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Pessoa")
	@ApiModelProperty(value = "Identificador único para Pessoa")
	private Pessoa pessoa;
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy="conta")
	private List<Transacao> transacoes;

	
	@Column(name = "saldo", nullable = false)
	@ApiModelProperty(value = "Digite o saldo da conta")
	private double saldo;
	
	@Column(nullable = false)
	@ApiModelProperty(value = "Digite o Limite de saque diário da conta")
	private double limiteSaqueDiario;
	
	@Column(nullable = false)
	@ApiModelProperty(value = "Digite se se o Flag está ativo(true) ou desativada(false)")
	private boolean flagAtivo;
	
	@Column(nullable = false)
	@ApiModelProperty(value = "Digite o Tipo da conta")
	private int tipoConta;
	
	@Column(name = "dataCriacao", nullable = false)
	@JsonFormat(pattern="dd/MM/yyyy")
	@ApiModelProperty(value = "Digite a Data de criação da conta")
	private Date dataCriacao;
}
