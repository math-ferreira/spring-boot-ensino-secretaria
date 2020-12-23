package com.springboot.apisecretaria.api.exception;

import lombok.Getter;

@Getter
public class SecretariaNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SecretariaNotFoundException(String mensagem) {
		super(mensagem);
	}

}