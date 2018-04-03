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

import com.br.ifma.logeasy.domain.Nivel;
import com.br.ifma.logeasy.services.NivelService;
import com.br.ifma.logeasy.utils.Utils;

@Component
@Path("/nivel")
public class NivelEndpoint {

	private NivelService nivelService;
		
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNivelById(@PathParam("id") Integer id) {
		Nivel nivel = nivelService.getNivelById(id);
		return Response.ok(nivel).build();
	}
	
	@GET
	@Path("/niveis")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNiveis() {
		List<Nivel> list = Utils.toList(nivelService.listAllNiveis()); 
		return Response.ok(list).build();
	}
	
	@Autowired
	public void setNivelService(NivelService nivelService) {
		this.nivelService = nivelService;
	}
}
