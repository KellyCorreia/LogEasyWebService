package com.br.ifma.logeasy.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.br.ifma.logeasy.endpoint.AlunoEndpoint;
import com.br.ifma.logeasy.endpoint.CursoEndpoint;
import com.br.ifma.logeasy.endpoint.ProfessorEndpoint;

@Component
@ApplicationPath("/logeasy-webservice")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ProfessorEndpoint.class);
		register(CursoEndpoint.class);
		register(AlunoEndpoint.class);
	}
}
