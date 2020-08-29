package com.springboot.boaspraticas.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.boaspraticas.apisecretaria.model.Endereco;

public class AlunoDTO {

    @JsonIgnore
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("idade")
    private Integer idade;

    @JsonProperty("periodo_letivo")
    private int periodo;

    @JsonProperty("endereco")
    private Endereco endereco;
}