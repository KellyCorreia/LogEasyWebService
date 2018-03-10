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

import com.br.ifma.logeasy.domain.Curso;
import com.br.ifma.logeasy.services.CursoService;
import com.br.ifma.logeasy.utils.Utils;

@Component
@Path("/curso")
public class CursoEndpoint {

	private CursoService cursoService;
	
	@GET
	@Path("/cursos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCursos() {
		List<Curso> list = Utils.toList(cursoService.listAllCursos()); 
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCursoById(@PathParam("id") Integer id) {
		Curso curso = cursoService.getCursoById(id);
		return Response.ok(curso).build();
	}
	
	@Autowired
    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }
}
