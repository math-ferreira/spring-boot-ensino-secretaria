package com.springboot.boaspraticas.apisecretaria.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.enums.PeriodoLetivo;

import org.modelmapper.ModelMapper;

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

    @JsonProperty("contatos")
    private List<ContatoDTO> contatos = new ArrayList<>();

    public static AlunoDTO create(Aluno aluno) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(aluno, AlunoDTO.class);
    }
}