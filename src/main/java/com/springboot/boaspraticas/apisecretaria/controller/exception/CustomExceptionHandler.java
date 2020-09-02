package com.springboot.boaspraticas.apisecretaria.controller.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { SecretariaException.class })
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    protected ErrorResponse objectNotFound(SecretariaException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.setHttpStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
        return error;
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ErrorResponse objectNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        String message = "Não foi possivel inserir o Aluno";
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError objectError : objectErrors) {
            String fieldErrors = ((FieldError) objectError).getField();
            message += ", " + fieldErrors + " " + objectError.getDefaultMessage();
        }

        ErrorResponse error = new ErrorResponse();
        error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(message);
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
        return error;
    }

}