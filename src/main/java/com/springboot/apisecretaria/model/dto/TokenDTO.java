package com.springboot.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {

	@JsonProperty("token")
	private String token;
	
	@JsonProperty("type")
	private String tipo;
	
	@JsonProperty("expiration")
	private String dataExpiracao;
	
	public TokenDTO(String token, String expiration) {
		this.token = token;
		this.tipo = "Bearer Token";
		this.dataExpiracao = expiration + " ms"; 
	}
	
}
