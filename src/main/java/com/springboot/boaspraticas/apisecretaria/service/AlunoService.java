package com.springboot.boaspraticas.apisecretaria.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.dto.AlunoDTO;
import com.springboot.boaspraticas.apisecretaria.repository.AlunoReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoReposity repository;

    public List<AlunoDTO> getAlunos() {
        List<Aluno> alunos = repository.findAll();
        List<AlunoDTO> alunosDTO = new ArrayList<>();
        for (Aluno aluno : alunos) {
            alunosDTO.add(AlunoDTO.create(aluno));
        }
        return alunosDTO;
    }
}