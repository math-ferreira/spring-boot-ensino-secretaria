package com.springboot.boaspraticas.apisecretaria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.boaspraticas.apisecretaria.api.exception.SecretariaException;
import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.Contato;
import com.springboot.boaspraticas.apisecretaria.model.dto.AlunoDTO;
import com.springboot.boaspraticas.apisecretaria.repository.AlunoReposity;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
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
            AlunoDTO alunoDTO = AlunoDTO.create(aluno.get());
            return alunoDTO;
        }
        throw new SecretariaException("Aluno de id " + id + " nao encontrado", HttpStatus.NOT_FOUND);
    }

    public AlunoDTO postAluno(Aluno aluno) {
        persistContato(aluno);
        repository.save(aluno);
        return AlunoDTO.create(aluno);
    }

    public AlunoDTO putAluno(Aluno aluno, Long id) {
        Optional<Aluno> alunoGravado = repository.findById(id);
        if (alunoGravado.isPresent()) {
            persistContato(aluno);
            merge(aluno, alunoGravado.get());
            repository.save(alunoGravado.get());
            return AlunoDTO.create(alunoGravado.get());
        }
        throw new SecretariaException("Não é possivel atualizar o Aluno. Id " + id + " não encontrado",
                HttpStatus.NOT_FOUND);
    }

    private Aluno persistContato(Aluno aluno) {
        List<Contato> contatos = aluno.getContatos();
        for (int i = 0; i < contatos.size(); i++) {
            aluno.getContatos().get(i).setAluno(aluno);
        }
        return aluno;
    }

	private Aluno merge(Aluno input, Aluno output) {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.getConfiguration().setDeepCopyEnabled(true);
		modelMapper.map(input, output);
		return output;
	}
}