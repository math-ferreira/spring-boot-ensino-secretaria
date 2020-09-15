package com.springboot.boaspraticas.apisecretaria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonProperty("rua")
    @NotBlank(message = "informe a rua")
    private String rua;

    @JsonProperty("bairro")
    @NotBlank(message = "informe o bairro")
    private String bairro;

    @JsonProperty("numero")
    @NotNull(message = "informe o numero")
    private Integer numero;

    @JsonProperty("cep")
    @NotBlank(message = "informe o cep")
    private String cep;

    @JsonProperty("cidade")
    @NotBlank(message = "informe a cidade")
    private String cidade;

    @JsonProperty("estado")
    @NotBlank(message = "informe o estado")
    private String estado;

    @OneToMany(mappedBy = "endereco")
    @JsonIgnore
    private List<Aluno> alunos = new ArrayList<>();

    public Endereco() {
    }

    public Endereco(Long id, String rua, String bairro, Integer numero, String cep, String cidade, String estado) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Endereco id(Long id) {
        this.id = id;
        return this;
    }

    public Endereco rua(String rua) {
        this.rua = rua;
        return this;
    }

    public Endereco bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public Endereco numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public Endereco cep(String cep) {
        this.cep = cep;
        return this;
    }

    public Endereco cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public Endereco estado(String estado) {
        this.estado = estado;
        return this;
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Endereco)) {
            return false;
        }
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(rua, endereco.rua)
                && Objects.equals(bairro, endereco.bairro) && Objects.equals(numero, endereco.numero)
                && Objects.equals(cep, endereco.cep) && Objects.equals(cidade, endereco.cidade)
                && Objects.equals(estado, endereco.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rua, bairro, numero, cep, cidade, estado);
    }

}