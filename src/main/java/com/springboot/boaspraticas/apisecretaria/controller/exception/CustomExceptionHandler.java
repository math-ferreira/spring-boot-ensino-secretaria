package com.springboot.boaspraticas.apisecretaria.controller.exception;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                new ObjectResponseException(HttpStatus.NOT_FOUND, "Objeto Aluno não encontrado", request), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

	@JsonPropertyOrder({ "httpStatus", "message", "path", "exception", "time_stamp" })
    class ObjectResponseException implements Serializable {

        private static final long serialVersionUID = 1L;

        @JsonProperty("http_status")
        private int httpStatus;

        @JsonProperty("mensagem")
        private String message;

        @JsonProperty("path_uri")
        private String path;

        public ObjectResponseException(HttpStatus httpStatus, String message, WebRequest request) {
            this.httpStatus = httpStatus.value();
            this.message = message;
            this.path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        }

        public String getMessage() {
            return message;
        }

        public int getHttpStatus() {
            return httpStatus;
        }

        public String getPath() {
            return path;
        }

        public String getException() {
            return ObjectNotFoundException.class.getName();
        }

        @JsonProperty("time_stamp")
        public long getTimeStamp() {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Instant instant = timestamp.toInstant();
            return Timestamp.from(instant).getTime();
        }

    }
}