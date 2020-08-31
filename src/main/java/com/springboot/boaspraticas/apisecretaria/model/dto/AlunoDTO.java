package com.springboot.boaspraticas.apisecretaria.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.Contato;
import com.springboot.boaspraticas.apisecretaria.model.Endereco;
import com.springboot.boaspraticas.apisecretaria.model.enums.PeriodoLetivo;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class AlunoDTO {

    private String nome;
    private Integer idade;
    private PeriodoLetivo periodo;
    private Endereco endereco;
    private List<Contato> contatos = new ArrayList<>();

    public static AlunoDTO create(Aluno aluno) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(aluno, AlunoDTO.class);
    }
}