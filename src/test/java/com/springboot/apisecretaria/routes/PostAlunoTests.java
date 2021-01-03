package com.springboot.apisecretaria.routes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.jms.JMSException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.apisecretaria.business.SalvarAlunoTests;
import com.springboot.apisecretaria.model.Aluno;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
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
class PostAlunoTests extends AutenticacaoTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String POST_AUTH = "/alunos";

	@BeforeEach
	public void gerarToken() throws Exception {
		requestToken();
	}

	@Test
	void postAluno200() throws Exception {
		// Cria objeto Request Body para API geracao de Token
		Aluno aluno = new SalvarAlunoTests().createAluno();

		// Requisicao a API POST Alunos
		ResultActions requestAuth = 
			mockMvc.perform(
				MockMvcRequestBuilders
					.post(POST_AUTH)
						.contentType("application/json")
						.headers(headerAuthorization())
					.content(objectMapper
						.writeValueAsString(aluno)));

		
		// Verifica HTTP status
		requestAuth.andExpect(
			MockMvcResultMatchers
				.status()
				.isCreated());
		

		// Guarda Objeto JSON retorno da API
		JSONObject JSONresponseBody = new JSONObject( requestAuth
								.andReturn()
								.getResponse()
								.getContentAsString());
															
		assertTrue(JSONresponseBody.has("token"));
	}
}
