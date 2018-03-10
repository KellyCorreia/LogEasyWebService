package com.br.ifma.logeasy.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the grupoconteudos database table.
 * 
 */
@Entity
@Table(name="curso")
public class Curso extends AbstractDomainClass implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String codigo;
    private String nome;
    private String descricao;

	//bi-directional many-to-one association to Disciplina
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name="iddisciplina")
	@JsonBackReference(value="cursos")
	private Disciplina disciplina;

	//bi-directional many-to-one association to CursoAluno
	@OneToMany(mappedBy="curso")
	@JsonIgnore
	private List<CursoAluno> alunosCurso;
	
	@OneToMany(mappedBy="curso", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonManagedReference(value="conteudos")
	private List<Conteudo> conteudos;

	public Curso() {
		this.disciplina = new Disciplina();
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CursoAluno> getAlunosCurso() {
		return alunosCurso;
	}

	public void setAlunosCurso(List<CursoAluno> alunosCurso) {
		this.alunosCurso = alunosCurso;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}	
}