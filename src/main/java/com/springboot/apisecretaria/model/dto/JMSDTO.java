package com.springboot.apisecretaria.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JMSDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Object content;
	private String request;
}
