package com.springboot.apisecretaria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.apisecretaria.api.exception.SecretariaException;
import com.springboot.apisecretaria.model.Aluno;
import com.springboot.apisecretaria.model.dto.AlunoDTO;
import com.springboot.apisecretaria.repository.AlunoReposity;

@Service
public class AlunoService {

    @Autowired
    private AlunoReposity repository;

    public List<AlunoDTO> getAlunos() {
        List<Aluno> alunos = repository.findAll();
        if (alunos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
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
            AlunoDTO alunoDTO = AlunoDTO.create(aluno.get());
            return alunoDTO;
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public AlunoDTO postAluno(Aluno aluno) {
        repository.save(aluno);
        return AlunoDTO.create(aluno);
    }

    public AlunoDTO putAluno(Aluno aluno, Long id) {
        Optional<Aluno> alunoGravado = repository.findById(id);
        if (alunoGravado.isPresent()) {
            merge(aluno, alunoGravado.get());
            repository.save(alunoGravado.get());
            return AlunoDTO.create(alunoGravado.get());
        }
        throw new SecretariaException("Não é possivel atualizar o Aluno. Id " + id + " não encontrado",
                HttpStatus.NOT_FOUND);
    }

    public void deleteAluno(Long id){
        if (repository.findById(id).isEmpty()){
            throw new SecretariaException("Não é possivel remover o Aluno. Id " + id + " não encontrado",
            HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    private Aluno merge(Aluno aluno, Aluno alunoGravado) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setDeepCopyEnabled(true);
        modelMapper.map(aluno, alunoGravado);
        return alunoGravado;
    }

}