package com.springboot.boaspraticas.apisecretaria.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable{

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("idade")
    private Integer idade;

    @JsonProperty("periodo_letivo")
    private Integer periodo;

    public Aluno(){

    }

    public Aluno(Long id, String nome, Integer idade, Integer periodo){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.periodo = periodo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Aluno)) {
            return false;
        }
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
    
}