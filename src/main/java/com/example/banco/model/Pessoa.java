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
@Table(name = "pessoa")
public class Pessoa implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1532936598172894183L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column()
	@ApiModelProperty(value = "Identificador único para a Pessoa")
	private long id;
	
	@Column(nullable = false, length = 100)
	@ApiModelProperty(value = "Digite o nome da pessoa")
	private String nome;
	
	@Column(unique = true, nullable = false)
	@ApiModelProperty(value = "Digite o cpf da pessoa")
	private String cpf;
	
	@Column(nullable = false)
	@JsonFormat(pattern="dd/MM/yyyy")
	@ApiModelProperty(value = "Digite a data de nascimento da pessoa")
	private Date dataNascimento;
	
	@JsonIgnoreProperties("pessoa")
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
	private List<Conta> contas;
}
