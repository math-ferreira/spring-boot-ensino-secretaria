package com.springboot.boaspraticas.apisecretaria.repository;

import com.springboot.boaspraticas.apisecretaria.model.Contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
    
}