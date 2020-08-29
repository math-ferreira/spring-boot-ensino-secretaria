package com.springboot.boaspraticas.apisecretaria.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PeriodoLetivo {

    @JsonProperty("primario")
    PRIMARIO(0),

    @JsonProperty("primeira serie")
    PRIMEIRA_SERIE(1),

    @JsonProperty("segunda serie")
    SEGUNDA_SERIE(2),

    @JsonProperty("terceira serie")
    TERCEIRA_SERIE(3),

    @JsonProperty("quarta serie")
    QUARTA_SERIE(4),

    @JsonProperty("quinta serie")
    QUINTA_SERIE(5),

    @JsonProperty("sexta serie")
    SEXTA_SERIE(6),

    @JsonProperty("setima serie")
    SETIMA_SERIE(7),

    @JsonProperty("oitava serie")
    OITAVA_SERIE(8),

    @JsonProperty("primeiro grau do ensino medio")
    PRIMEIRO_MEDIO(11),

    @JsonProperty("segundo grau do ensino medio")
    SEGUNDO_MEDIO(12),

    @JsonProperty("terceiro grau do ensino medio")
    TERCEIRO_MEDIO(13);

    private int code;

    private PeriodoLetivo(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public static PeriodoLetivo valueOf(int code){
        for (PeriodoLetivo periodoLetivo : PeriodoLetivo.values()) {
            if (periodoLetivo.getCode() == code){
                return periodoLetivo;
            }
        }
        throw new IllegalArgumentException("Codigo para Periodo Letivo não encontrado");
    }
    
}