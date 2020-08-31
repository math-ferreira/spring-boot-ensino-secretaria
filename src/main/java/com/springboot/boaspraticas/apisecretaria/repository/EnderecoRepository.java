package com.springboot.boaspraticas.apisecretaria.repository;

import com.springboot.boaspraticas.apisecretaria.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}