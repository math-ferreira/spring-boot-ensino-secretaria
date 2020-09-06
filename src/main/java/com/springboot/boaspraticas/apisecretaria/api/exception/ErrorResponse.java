package com.springboot.boaspraticas.apisecretaria.api.exception;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "httpStatus", "message", "path", "exception", "time_stamp" })
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("http_status")
    private int httpStatus;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("path_uri")
    private String path;

    @JsonProperty("time_stamp")
    public long getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return Timestamp.from(instant).getTime();
    }

}
