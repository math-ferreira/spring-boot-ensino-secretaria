package com.springboot.boaspraticas.apisecretaria.controller;

import javax.servlet.http.HttpServletRequest;

import com.springboot.boaspraticas.apisecretaria.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AlunosController {

    @Autowired
    private AlunoService service;

    @GetMapping("/alunos")
    public ResponseEntity<?> getAlunos(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAlunos());
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<?> getAlunosById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAlunoById(id));
    }

}