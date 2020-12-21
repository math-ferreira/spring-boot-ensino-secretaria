package com.springboot.apisecretaria.model.dto;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.apisecretaria.model.Contato;

import lombok.Data;

@Data
public class ContatoDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("numero_telefone")
    private String telefone;

    @JsonProperty("tipo_telefone")
    private String tipoTelefone;

    public static ContatoDTO create(Contato contato) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(contato, ContatoDTO.class);
    }
}
