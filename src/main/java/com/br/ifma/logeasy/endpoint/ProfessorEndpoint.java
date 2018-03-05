package com.br.ifma.logeasy.endpoint;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.ifma.logeasy.domain.Professor;
import com.br.ifma.logeasy.services.ProfessorService;
import com.br.ifma.logeasy.utils.Utils;

@Component
@Path("/professor")
public class ProfessorEndpoint {

	private ProfessorService professorService;
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfessorDetails() {
		List<Professor> list = Utils.toList(professorService.listAllProfessors()); 
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfessorById(@PathParam("id") Integer id) {
		Professor professor = professorService.getProfessorById(id);
		return Response.ok(professor).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor professor) {
		professorService.saveProfessor(professor);
        return Response.created(URI.create("/logeasy-webservice/professor/"+ professor.getId())).build();
	}	
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateProfessor(Professor professor) {
		professorService.saveProfessor(professor);
		return Response.ok(professor).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteProfessor(@PathParam("id") Integer id) {
		professorService.deleteProfessor(id);
		return Response.noContent().build();
	}	
	
	@Autowired
    public void setProfessorService(ProfessorService professorService) {
        this.professorService = professorService;
    }
}
