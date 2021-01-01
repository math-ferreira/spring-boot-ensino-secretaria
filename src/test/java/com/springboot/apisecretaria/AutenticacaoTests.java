package com.springboot.apisecretaria;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.apisecretaria.api.config.security.form.LoginForm;
import com.springboot.apisecretaria.model.Usuario;
import com.springboot.apisecretaria.repository.UsuarioRepository;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.Getter;

@SpringBootTest
@AutoConfigureMockMvc
class AutenticacaoTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final String POST_AUTH = "/auth";
	private static final String NOME_AUTH = "fulano";
	private static final String EMAIL_AUTH = "fulano@uol.com";
	private static final String SENHA_AUTH = "senha123";

	@Getter
	private String token;

	private String cryptPassword(String password) {
		BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
		return cript.encode(password);
	}

	private void saveUser(Usuario usuario) {
		usuarioRepository.save(usuario);

	}

	private boolean createUser() {
		try {
			Usuario usuario = new Usuario(1L, NOME_AUTH, EMAIL_AUTH, cryptPassword(SENHA_AUTH));
			saveUser(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Test
	void requestToken() throws Exception {
		createUser(); // Cria usuario no BD para geracao de token

		LoginForm login = new LoginForm(); // Cria objeto Request Body para API geracao de Token
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
		this.token = JSONresponseBody.getString("token");
	}

}
