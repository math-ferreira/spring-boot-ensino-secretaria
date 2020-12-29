package com.springboot.apisecretaria.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.apisecretaria.api.config.security.form.LoginForm;
import com.springboot.apisecretaria.model.dto.TokenDTO;
import com.springboot.apisecretaria.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private TokenService tokenService;

	@Value("${jwt.token.expiration}")
	private String expiration;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm loginForm) {
		String token = tokenService.getToken(loginForm);
		return ResponseEntity.ok(new TokenDTO(token, expiration));

	}

}
