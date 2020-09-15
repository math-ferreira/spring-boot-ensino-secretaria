package com.springboot.boaspraticas.apisecretaria.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"rua", "bairro", "numero", "cep", "cidade", "estado"})
public class EnderecoDTO {
    
    @JsonProperty("rua")
    private String rua;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estado;

}
