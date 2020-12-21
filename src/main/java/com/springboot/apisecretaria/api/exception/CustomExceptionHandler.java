package com.springboot.apisecretaria.api.exception;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

    @ExceptionHandler(value = { SecretariaException.class })
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    protected ErrorResponse objectNotFound(SecretariaException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.setHttpStatus(ex.getStatusCode().value());
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
        return error;
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class, NumberFormatException.class })
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ErrorResponse teste(Exception ex, WebRequest request) {
        String message = "Nao foi possivel atender a solicitacao";
        if (ex.getClass().equals(MethodArgumentNotValidException.class)) {
            List<ObjectError> objectErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError objectError : objectErrors) {
                message += ", " + objectError.getDefaultMessage();
            }
        } else if (ex.getClass().equals(ConstraintViolationException.class)){
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) ex).getConstraintViolations();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                message += ", " + constraintViolation.getMessage();
            }
        } else if (ex.getClass().equals(HttpMessageNotReadableException.class)) {
            message = "Verique o request body, ha erro de sintaxe ou campos informados incorretamente";
        }

        ErrorResponse error = new ErrorResponse();
        error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(message);
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
        return error;
    }

}