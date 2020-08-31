package com.springboot.boaspraticas.apisecretaria.controller;

import java.util.List;

import com.springboot.boaspraticas.apisecretaria.model.dto.AlunoDTO;
import com.springboot.boaspraticas.apisecretaria.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AlunosController {

    @Autowired
    private AlunoService service;

    @GetMapping("/alunos")
    public List<AlunoDTO> getAlunos(){
        return service.getAlunos();
    }
    
}