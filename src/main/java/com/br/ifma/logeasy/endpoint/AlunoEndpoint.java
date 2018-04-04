package com.br.ifma.logeasy.endpoint;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.ifma.logeasy.domain.AlternativaAluno;
import com.br.ifma.logeasy.domain.Aluno;
import com.br.ifma.logeasy.domain.Avatar;
import com.br.ifma.logeasy.domain.Professor;
import com.br.ifma.logeasy.services.AlternativaService;
import com.br.ifma.logeasy.services.AlunoService;
import com.br.ifma.logeasy.services.AvatarService;
import com.br.ifma.logeasy.utils.Utils;

@Component
@Path("/aluno")
public class AlunoEndpoint {

	private AlunoService alunoService;
	private AvatarService avatarService;
	private AlternativaService alternativaService;
	
	@GET
	@Path("/alunos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlunos() {
		List<Aluno> list = Utils.toList(alunoService.listAllAlunos()); 
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlunoById(@PathParam("id") Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		return Response.ok(aluno).build();
	}
	
/*	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAluno(Aluno aluno) {
		AlternativaAluno item;
		Aluno novoAluno = aluno;
		novoAluno.setId(null);
		novoAluno.getUsuario().setId(null);
		
		Avatar avatar = avatarService.getAvatarById(aluno.getAvatar().getId());
		novoAluno.setAvatar(avatar);
		novoAluno.setAlternativasAluno(new HashSet<AlternativaAluno>());
		
		for(AlternativaAluno aa: aluno.getAlternativasAluno()) {
			item = aa;
			item.setId(null);
			item.setAluno(novoAluno);
			item.setAlternativa(alternativaService.getAlternativaById(item.getAlternativa().getId()));
			novoAluno.getAlternativasAluno().add(item);
		}
		 
		alunoService.saveAluno(novoAluno);
        return Response.created(URI.create("/logeasy-webservice/aluno/"+ aluno.getId())).build();
	}	*/
	
	@POST
	@Path("/addAlunos")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAlunos(@RequestBody List<Aluno> alunos) {
		Aluno novoAluno = null;
		Avatar avatar = null;
		AlternativaAluno item = null;
		
		for(Aluno a:alunos) {
			novoAluno = a;
			novoAluno.setId(null);
			novoAluno.getUsuario().setId(null);
			
			avatar = avatarService.getAvatarById(a.getAvatar().getId());
			novoAluno.setAvatar(avatar);
			novoAluno.setAlternativasAluno(new ArrayList<AlternativaAluno>());
			
			for(AlternativaAluno aa: a.getAlternativasAluno()) {
				System.out.println("Alternativa:===> " + aa.getId() + " , " + aa.getAlternativa().getTexto() + " , " + aa.getAlternativa().getId());
				item = aa;
				item.setId(null);
				item.setAluno(novoAluno);
				item.setAlternativa(alternativaService.getAlternativaById(item.getAlternativa().getId()));
				novoAluno.getAlternativasAluno().add(item);
				System.out.println("Alternativa_aluno:===> " + item.getAlternativa().getTexto() + " , " + item.getAluno().getNome());
			}
			//verificar se já existe um aluno com o mesmo email no banco
			Iterable<Aluno> todosAlunos = alunoService.listAllAlunos();
			for(Aluno aluno : todosAlunos) {
				if(a.getUsuario().getEmail().equals(aluno.getUsuario().getEmail())) {
					alunoService.deleteAluno(aluno.getId());
				}
			}
			alunoService.saveAluno(novoAluno);
			System.out.println("Aluno:===> " + a.getNome() + " , " + a.getAlternativasAluno().size());
		}
		return Response.created(URI.create("/logeasy-webservice/aluno/1")).build();
	}
	
	
	/*@POST
	@Path("/addAlunos")
	@Consumes(MediaType.APPLICATION_JSON)
	public Aluno addAlunos(@RequestBody Aluno a) {
		Iterable<Aluno>alunosRemoto = alunoService.listAllAlunos();
		//List<Response> retorno = new ArrayList<Response>();
		boolean existeNoBanco = false;
		//Response r = null;
		
		//for(Aluno a : alunos) {
			for(Aluno ar : alunosRemoto) {
				if(a.getNome().equals(ar.getNome())) {
					existeNoBanco = true;
				}
			}
			if(!existeNoBanco){
				a = alunoService.saveAluno(a);
				Response.created(URI.create("/logeasy-webservice/aluno/"+ a.getId())).build();
			}
		return a;
	}	*/
	
	@Autowired
    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
	
	@Autowired
    public void setAvatarService(AvatarService avatarService) {
        this.avatarService = avatarService;
    }
	
	@Autowired
    public void setAlternativaService(AlternativaService alternativaService) {
        this.alternativaService = alternativaService;
    }

	
}
