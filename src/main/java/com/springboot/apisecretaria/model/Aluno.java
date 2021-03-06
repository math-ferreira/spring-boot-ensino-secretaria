package com.springboot.apisecretaria.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.apisecretaria.model.enums.PeriodoLetivo;

@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonProperty("nome")
    @NotBlank(message = "informe o nome")
    private String nome;

    @JsonProperty("numero_matricula")
    @NotBlank(message = "informe o numero de matricula")
    private String numeroMatricula;

    @JsonProperty("idade")
    @NotNull(message = "informe a idade")
    private Integer idade;

    @JsonProperty("periodo_letivo")
    @NotNull(message = "informe o periodo letivo")
    private Integer periodo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @JsonProperty("endereco")
    @NotNull(message = "informe o endereco")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id")
    @JsonProperty("contato")
    @NotNull(message = "informe o contato")
    private Contato contato;

    public Aluno() {

    }

    public Aluno(Long id, String nome, String numeroMatricula, Integer idade, PeriodoLetivo periodo,
            Endereco endereco, Contato contato) {
        this.id = id;
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
        this.idade = idade;
        setPeriodo(periodo);
        this.endereco = endereco;
        this.contato = contato;
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

    public String getNumeroMatricula() {
        return this.numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public PeriodoLetivo getPeriodo() {
        return PeriodoLetivo.valueOf(periodo);
    }

    public void setPeriodo(PeriodoLetivo periodo) {
        this.periodo = periodo.getCode();
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return this.contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
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

    @Override
    public String toString() {
        return "[nome="+ this.nome + ", matricula="+ this.numeroMatricula + "]";
    }

}