package com.springboot.apisecretaria.api.config.security.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

	@NotBlank(message = "informe seu email")
	@JsonProperty("email")
	private String email;

	@NotBlank(message = "informe sua senha")
	@JsonProperty("senha")
	private String senha;

	public UsernamePasswordAuthenticationToken create() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
