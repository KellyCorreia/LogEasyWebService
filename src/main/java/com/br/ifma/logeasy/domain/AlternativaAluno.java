package com.br.ifma.logeasy.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the alternativa_aluno database table.
 * 
 */
@Entity
@Table(name="alternativa_aluno")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=AlternativaAluno.class)
public class AlternativaAluno extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Alternativa
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="idalternativa")
	private Alternativa alternativa;

	//bi-directional many-to-one association to Aluno
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="idaluno")
	private Aluno aluno;
	
	private Calendar dataHora;

	public AlternativaAluno() {
	}

	public Alternativa getAlternativa() {
		return this.alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

}