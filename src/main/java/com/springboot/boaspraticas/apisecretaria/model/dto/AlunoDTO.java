package com.springboot.boaspraticas.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.Endereco;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
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

    public static AlunoDTO create(Aluno aluno) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(aluno, AlunoDTO.class);
    }
}