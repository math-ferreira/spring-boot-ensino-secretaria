package com.springboot.apisecretaria.model.dto;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springboot.apisecretaria.model.Aluno;
import com.springboot.apisecretaria.model.enums.PeriodoLetivo;

import lombok.Data;

@Data
@JsonPropertyOrder({"nome", "idade", "numero_matricula", "periodo_letivo", "endereco", "contatos"})
public class AlunoDTO {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("idade")
    private Integer idade;

    @JsonProperty("numero_matricula")
    private String numeroMatricula;

    @JsonProperty("periodo_letivo")
    private PeriodoLetivo periodo;

    @JsonProperty("endereco")
    private EnderecoDTO endereco;

    @JsonProperty("contato")
    private ContatoDTO contato;

    public static AlunoDTO create(Aluno aluno) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(aluno, AlunoDTO.class);
    }
}