package com.ty.zenxl.handler;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;
import static com.ty.zenxl.pojos.ZenxlConstantData.IS_ERROR_TRUE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.zenxl.response.ZenxlErrorMessage;

/**
 * Defines exception handling mechanism for all raised exception in the
 * application.
 * 
 * @author Indrajit
 * @author Abhishek
 * @version 1.0
 */

@RestControllerAdvice
public class ZenxlExceptionHandler extends ResponseEntityExceptionHandler {

	private Map<String, Object> validationError = new HashMap<>();

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ZenxlErrorMessage globalExceptionHandler(Exception exception) {
		return new ZenxlErrorMessage(IS_ERROR_TRUE, exception.getMessage());
	}

	@Override
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> validationError = new HashMap<>();
		List<String> errorList = exception.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		validationError.put(VALIDATION_ERROR, errorList);
		return ResponseEntity.badRequest().body(validationError);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
		List<String> violationErrors = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());

		validationError.put(IS_ERROR, IS_ERROR_TRUE);
		validationError.put(VALIDATION_ERROR, violationErrors);
		return ResponseEntity.badRequest().body(validationError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = exception.getMessage();
		if (message != null) {
			message = message.split(":")[0];
		}
		validationError.put(IS_ERROR, IS_ERROR_TRUE);
		validationError.put(VALIDATION_ERROR, message);
		return ResponseEntity.badRequest().body(validationError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		validationError.put(IS_ERROR, IS_ERROR_TRUE);
		validationError.put(VALIDATION_ERROR, REQUIRED_REQUEST_PART_NOT_FOUND);
		return ResponseEntity.badRequest().body(validationError);
	}

	@ExceptionHandler(value = MissingRequestHeaderException.class)
	public ResponseEntity<Object> httpHeaderExceptionHandler(MissingRequestHeaderException exception) {

		validationError.put(IS_ERROR, IS_ERROR_TRUE);
		validationError.put(VALIDATION_ERROR, REQUIRED_REQUEST_HEADER_NOT_FOUND);
		return ResponseEntity.badRequest().body(validationError);
	}
}
