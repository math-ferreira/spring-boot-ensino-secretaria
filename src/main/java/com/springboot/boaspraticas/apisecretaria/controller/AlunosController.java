package com.springboot.boaspraticas.apisecretaria.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.springboot.boaspraticas.apisecretaria.model.Aluno;
import com.springboot.boaspraticas.apisecretaria.model.dto.AlunoDTO;
import com.springboot.boaspraticas.apisecretaria.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping()
@Validated
public class AlunosController {

    @Autowired
    private AlunoService service;

    @GetMapping("/alunos")
    public ResponseEntity<List<AlunoDTO>> getAlunos(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAlunos());
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<AlunoDTO> getAlunosById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAlunoById(id));
    }

    @PostMapping("/alunos")
    public ResponseEntity<?> postAluno(@Valid @RequestBody Aluno aluno) {
        service.postAluno(aluno);
        return ResponseEntity
            .created(getUri(aluno.getId()))
            .build();
    }

    private URI getUri(Long id) {
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return location;
    }

}