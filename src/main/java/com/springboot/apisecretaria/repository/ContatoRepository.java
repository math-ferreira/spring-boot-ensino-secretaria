package com.springboot.apisecretaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.apisecretaria.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
    
}