package com.br.ifma.logeasy.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.br.ifma.logeasy.domain.Aluno;
import com.br.ifma.logeasy.domain.AmbienteAvatar;
import com.br.ifma.logeasy.domain.Avatar;
import com.br.ifma.logeasy.domain.Curso;
import com.br.ifma.logeasy.domain.Nivel;
import com.br.ifma.logeasy.domain.Professor;

public class JerseyClient {
	
	public void getProfessorDetails() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/professor");
		WebTarget details = base.path("details");
		List<Professor> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Professor>>() {});
		
	    list.stream().forEach(professor -> System.out.println(professor.getId()+", "+ professor.getNome()));
	    
	    client.close();
	}
	
	public void getProfessorById(int professorId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/professor");
		WebTarget professorById = base.path("{id}").resolveTemplate("id", professorId);
		Professor professor = professorById.request(MediaType.APPLICATION_JSON).get(Professor.class);
		
		System.out.println(professor.getId()+", "+ professor.getNome());
        
	    client.close();
	}
	
	public void addProfessor(Professor professor) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/professor");
		WebTarget add = base.path("add");
		Response response = add.request(MediaType.APPLICATION_JSON).post(Entity.json(professor));
		
		System.out.println("Response Http Status: "+ response.getStatus());
        System.out.println(response.getLocation());
        
	    client.close();
	}
	
	public void updateProfessor(Professor professor) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/professor");
		WebTarget update = base.path("update");
		Response response = update.request(MediaType.APPLICATION_JSON).put(Entity.json(professor));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		Professor resProfessor = response.readEntity(Professor.class);
		System.out.println(resProfessor.getId()+", "+ resProfessor.getNome());
        
	    client.close();
	}
	
	public void deleteProfessor(int professorId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/professor");
		WebTarget deleteById = base.path("{id}").resolveTemplate("id", professorId);
		Response response = deleteById.request(MediaType.APPLICATION_JSON).delete();
		
		System.out.println("Response Http Status: "+ response.getStatus());
		if(response.getStatus() == 204) {
			System.out.println("Data deleted successfully.");
		}
        
	    client.close();
	}	
	
	public void getCursoById(int cursoId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/curso");
		WebTarget cursoById = base.path("{id}").resolveTemplate("id", cursoId);
		Curso curso = cursoById.request(MediaType.APPLICATION_JSON).get(Curso.class);
		
		System.out.println("CURSO==>" + curso.getId()+", "+ curso.getNome());
		System.out.println("CONTEUDOS==>" + curso.getConteudos());
		System.out.println("DISCIPLINA==>" + curso.getDisciplina());
        
	    client.close();
	}
	
	public void getAlunos() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/aluno");
		WebTarget details = base.path("alunos");
		List<Aluno> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Aluno>>() {});
		
	    list.stream().forEach(aluno -> System.out.println(aluno.getId()+", "+ aluno.getNome()));
	    
	    client.close();
	}
	
	public void getNiveis() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/curso");
		WebTarget details = base.path("niveis");
		List<Nivel> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Nivel>>() {});
		
	    list.stream().forEach(nivel -> System.out.println(nivel.getDescricao()+", "+ nivel.getAmbiente().getElemento()));
	    
	    client.close();
	}
	
	public void getAvatars() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/logeasy-webservice/curso");
		WebTarget details = base.path("avatars");
		List<Avatar> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Avatar>>() {});
		
	    list.stream().forEach(avatar -> System.out.println(avatar.getCaracteristica()+", "+ avatar.getNome()));
	    for(Avatar av: list) {
	    	for(AmbienteAvatar aav: av.getAmbientesAvatar()) {
	    		System.out.println("Ambiente" + aav.getFalaInicialNivel());
	    		System.out.println("Ambiente" + aav.getAmbiente().getDescricao());
	    	}
	    }
	    client.close();
	}
	
	public static void main(String[] args) {
		JerseyClient jerseyClient = new JerseyClient();
	    //jerseyClient.getProfessorDetails();
		//jerseyClient.getProfessorById(1);
		//jerseyClient.getCursoById(1);
		jerseyClient.getNiveis();
		//jerseyClient.getAvatars();
		//jerseyClient.getAlunos();
		
		/*
		User user = new User();
        user.setUsername("userteste");
        user.setPassword("userteste");
        Professor professor = new Professor();
        professor.setCodigo("PROF002");
        professor.setNome("Kelly");
        professor.setUsuario(user);
        
		jerseyClient.addProfessor(professor);
		*/
		
		//jerseyClient.updateProfessor(professor);
		
		//jerseyClient.deleteProfessor(105);
	}
}
