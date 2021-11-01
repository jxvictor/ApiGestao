package com.example.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Pessoa")
	private Pessoa pessoa;
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy="conta")
	private List<Transacao> transacoes;

	@Column(name = "saldo", nullable = false)
	private double saldo;
	
	@Column(nullable = false)
	private double limiteSaqueDiario;
	
	@Column(nullable = false)
	private boolean flagAtivo;
	
	@Column(nullable = false)
	private int tipoConta;
	
	@Column(name = "dataCriacao", nullable = false)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataCriacao;
	
}
