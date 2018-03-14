package com.br.ifma.logeasy.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the avatar database table.
 * 
 */
@Entity
@Table(name="avatar")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Avatar.class)
public class Avatar extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;

	 private String nome;
	 private String caracteristica;

	//bi-directional many-to-one association to Aluno
	@OneToMany(mappedBy="avatar")
	@JsonIgnore
	private List<Aluno> alunos;

	//bi-directional many-to-one association to TemaAvatar
	@OneToMany(mappedBy="avatar", fetch = FetchType.EAGER)
	private List<AmbienteAvatar> ambientesAvatar;

	public Avatar() {
	}

	public String getCaracteristica() {
		return this.caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<AmbienteAvatar> getAmbientesAvatar() {
		return ambientesAvatar;
	}

	public void setAmbientesAvatar(List<AmbienteAvatar> ambientesAvatar) {
		this.ambientesAvatar = ambientesAvatar;
	}
}