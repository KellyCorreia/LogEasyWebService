package com.br.ifma.logeasy.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.ifma.logeasy.domain.Aluno;
import com.br.ifma.logeasy.services.AlunoService;
import com.br.ifma.logeasy.utils.Utils;

@Component
@Path("/aluno")
public class AlunoEndpoint {

	private AlunoService alunoService;
	
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
	
	@Autowired
    public void setAlunoService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
}
