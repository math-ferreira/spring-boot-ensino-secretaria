package com.springboot.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmailDTO {
	
	@JsonProperty("status_email")
	private String statusEmail;
	
	@JsonProperty("status_domain")
	private String statusDomain;
	
	@JsonProperty("format_valid")
	private boolean formatoValido;
}
