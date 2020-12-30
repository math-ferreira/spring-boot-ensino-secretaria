package com.springboot.apisecretaria.api.exception;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.springboot.apisecretaria.SecretariaApplication;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

	private static Logger logger = LoggerFactory.getLogger(SecretariaApplication.class);

	@ExceptionHandler(value = { SecretariaNotFoundException.class })
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	protected ErrorResponse objectNotFound(SecretariaNotFoundException ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), path);
		logger.error(error.toString());
		return error;
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
			ConstraintViolationException.class, NumberFormatException.class, IllegalArgumentException.class, AuthenticationException.class, BadCredentialsException.class, SignatureException.class})
	@ResponseBody
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	protected ErrorResponse badRequest(Exception ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		String message = customMessage(ex);
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, path);
		logger.error(error.toString());
		return error;
	}
	
	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	protected ErrorResponse methodNotAllowed(Exception ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorResponse error = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Metodo nao suportado ou nao definido para essa rota", path);
		logger.error(error.toString());
		return error;
	}


	private String customMessage(Exception ex){
		String message = "Nao foi possivel atender a solicitacao.";
		if (ex.getClass().equals(MethodArgumentNotValidException.class)) {
			List<ObjectError> objectErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : objectErrors) {
				message += ", " + objectError.getDefaultMessage();
			}
		} else if (ex.getClass().equals(ConstraintViolationException.class)) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) ex)
					.getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				message += ", " + constraintViolation.getMessage();
			}
		} else if (ex.getClass().equals(HttpMessageNotReadableException.class)) {
			message += "Verifique o request body, ha erro de sintaxe ou campos informados incorretamente";
		} else if (ex.getClass().equals(IllegalArgumentException.class)) {
			message += " " + ex.getMessage();
		} else if (ex.getClass().equals(BadCredentialsException.class)){
			message += " Credenciais invalidas.";
		} else if(ex.equals(SignatureException.class) || ex.equals(java.security.SignatureException.class)){
			message += " " + ex.getMessage();
		}
		return message;
	}

}