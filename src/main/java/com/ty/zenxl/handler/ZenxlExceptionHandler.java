package com.ty.zenxl.handler;

import static com.ty.zenxl.pojos.ZenxlConstantData.IS_ERROR_TRUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.zenxl.pojos.ZenxlErrorMessage;

/**
 * Defines exception handling mechanism for all raised exception in the application.
 * 
 * @author Indrajit
 * @version 1.0
 */

@RestControllerAdvice
public class ZenxlExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ZenxlErrorMessage globalExceptionHandler(Exception exception) {
		return new ZenxlErrorMessage(IS_ERROR_TRUE, exception.getMessage());
	}

	@Override
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> errorMap = new HashMap<>();
		List<String> errors = new ArrayList<>();
		
		errors = ex.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
		
		errorMap.put("isError", IS_ERROR_TRUE);
		errorMap.put("validationError", errors);
		
		
		return ResponseEntity.badRequest().body(errorMap);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("isError", IS_ERROR_TRUE);
		errorMap.put("Error", ex.getMessage());

		return ResponseEntity.badRequest().body(errorMap);
	}
	
	
}
