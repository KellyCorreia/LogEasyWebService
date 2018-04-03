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

import com.br.ifma.logeasy.domain.AmbienteAvatar;
import com.br.ifma.logeasy.domain.Avatar;
import com.br.ifma.logeasy.domain.Curso;
import com.br.ifma.logeasy.domain.Nivel;
import com.br.ifma.logeasy.services.AvatarService;
import com.br.ifma.logeasy.services.CursoService;
import com.br.ifma.logeasy.services.NivelService;
import com.br.ifma.logeasy.utils.Utils;

@Component
@Path("/curso")
public class CursoEndpoint {

	private CursoService cursoService;
	private NivelService nivelService;
	private AvatarService avatarService;
	
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
	
	@GET
	@Path("/niveis")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNiveis() {
		List<Nivel> list = Utils.toList(nivelService.listAllNiveis()); 
		for(Nivel n : list) {
			for(AmbienteAvatar aa : n.getAmbiente().getAmbientesAvatar()) {
				System.out.println("avatar: " + aa.getAvatar().getId());
				System.out.println("avatar: " + aa.getAvatar().getNome());
				System.out.println("avatar: " + aa.getAvatar().getCaracteristica());
			}
		}
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/avatars")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvatars() {
		List<Avatar> list = Utils.toList(avatarService.listAllAvatars()); 
		return Response.ok(list).build();
	}
	
	@Autowired
    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }
	@Autowired
	public void setNivelService(NivelService nivelService) {
		this.nivelService = nivelService;
	}
	@Autowired
	public void setAvatarService(AvatarService avatarService) {
		this.avatarService = avatarService;
	}
	
}
