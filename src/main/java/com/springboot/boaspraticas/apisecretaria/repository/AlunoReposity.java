package com.springboot.boaspraticas.apisecretaria.repository;

import com.springboot.boaspraticas.apisecretaria.model.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoReposity extends JpaRepository <Aluno, Long>{
    
}