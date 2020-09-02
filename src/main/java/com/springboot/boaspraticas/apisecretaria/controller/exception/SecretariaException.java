package com.springboot.boaspraticas.apisecretaria.controller.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class SecretariaException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private HttpStatus statusCode;

    public SecretariaException(String mensagem, HttpStatus statusCode){
        super(mensagem);
        this.statusCode = statusCode;
    }


}