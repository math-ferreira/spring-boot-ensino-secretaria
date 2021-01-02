package com.springboot.apisecretaria.routes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.apisecretaria.api.config.security.form.LoginForm;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PostAlunosTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private AutenticacaoTests autenticacao = new AutenticacaoTests();

	private static final String POST_AUTH = "/alunos";
	private static final String EMAIL_AUTH = "fulano@uol.com";
	private static final String SENHA_AUTH = "senha123";
	
	private String token;

	@Test
	void requestToken() throws Exception {
		autenticacao.requestToken();
		this.token = autenticacao.getToken(); 
		// Cria objeto Request Body para API geracao de Token
		LoginForm login = new LoginForm(); 
		login.setEmail(EMAIL_AUTH);
		login.setSenha(SENHA_AUTH);
							
		// Requisicao a API auth
		ResultActions requestAuth = mockMvc.perform(
			MockMvcRequestBuilders
				.post(POST_AUTH)
        		.contentType("application/json")
				.content(objectMapper
					.writeValueAsString(login)));	
			
		// Verifica HTTP status
		requestAuth.andExpect(
			MockMvcResultMatchers
				.status()
				.isOk());
		

		// Guarda Objeto JSON retorno da API
		JSONObject JSONresponseBody = new JSONObject( requestAuth
								.andReturn()
								.getResponse()
								.getContentAsString());
															
		assertTrue(JSONresponseBody.has("token"));
	}

}
