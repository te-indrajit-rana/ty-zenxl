package com.ty.zenxl.exception;

/**
 * Raised only if a {@code Status} is not found with the mentioned code type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class StatusNotFoundException extends RuntimeException {

	public StatusNotFoundException(String message) {
		super(message);
	}
}
