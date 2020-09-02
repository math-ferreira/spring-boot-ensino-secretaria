package com.springboot.boaspraticas.apisecretaria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.boaspraticas.apisecretaria.controller.exception.SecretariaException;
import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.dto.AlunoDTO;
import com.springboot.boaspraticas.apisecretaria.repository.AlunoReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoReposity repository;

    public List<AlunoDTO> getAlunos() {
        List<Aluno> alunos = repository.findAll();
        if (alunos.isEmpty()) {
            throw new SecretariaException("Não há registro de alunos", HttpStatus.NOT_FOUND);
        }
        List<AlunoDTO> alunosDTO = new ArrayList<>();
        for (Aluno aluno : alunos) {
            alunosDTO.add(AlunoDTO.create(aluno));
        }
        return alunosDTO;
    }

    public AlunoDTO getAlunoById(Long id) {
        Optional<Aluno> aluno = repository.findById(id);
        if (aluno.isPresent()) {
            return AlunoDTO.create(aluno.get());
        }
        throw new SecretariaException("Aluno de id " + id + " nao encontrado", HttpStatus.NOT_FOUND);
    }

    public AlunoDTO postAluno(Aluno aluno){
        repository.save(aluno);
        return AlunoDTO.create(aluno);
    }
}