package com.peter.helpdesk.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();

	    ex.getConstraintViolations().forEach(violation -> {
	        String fieldName = violation.getPropertyPath().toString();
	        String errorMessage = violation.getMessage();
	        errors.put(fieldName, errorMessage);
	    });

	    // Customizando a mensagem geral aqui
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "Validação de campos falhou. Por favor, corrija os erros abaixo.");
	    response.put("errors", errors);

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Aqui você retorna um Map<String, Object>
	}
	
}
