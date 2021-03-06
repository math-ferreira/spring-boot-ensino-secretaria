package com.springboot.apisecretaria.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonProperty("email")
    @NotBlank(message = "informe o email")
    private String email;

    @JsonProperty("numero_telefone")
    @NotBlank(message = "informe o numero de telefone")
    private String telefone;

    @JsonProperty("tipo_telefone")
    @NotBlank(message = "informe o tipo de telefone")
    private String tipoTelefone;

    @OneToOne(mappedBy = "contato")
    private Aluno aluno;

    public Contato() {
    }

    public Contato(Long id, String email, String telefone, String tipoTelefone, Aluno aluno) {
        this.id = id;
        this.email = email;
        this.telefone = telefone;
        this.tipoTelefone = tipoTelefone;
        this.aluno = aluno;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return this.tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Contato)) {
            return false;
        }
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(email, contato.email)
                && Objects.equals(telefone, contato.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, telefone);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", email='" + getEmail() + "'" + ", telefone='" + getTelefone() + "'"
                + "}";
    }

}