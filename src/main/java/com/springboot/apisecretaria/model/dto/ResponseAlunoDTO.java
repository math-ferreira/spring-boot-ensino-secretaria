package com.springboot.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.apisecretaria.model.Aluno;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseAlunoDTO {

    @JsonProperty("id_registro")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("numero_matricula")
    private String numeroMatricula;

    public static ResponseAlunoDTO create(Aluno aluno){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(aluno, ResponseAlunoDTO.class);
    }
}
