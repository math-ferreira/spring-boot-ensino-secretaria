package com.springboot.boaspraticas.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContatoDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("numero_telefone")
    private String telefone;

    @JsonProperty("tipo_telefone")
    private String tipoTelefone;

}
