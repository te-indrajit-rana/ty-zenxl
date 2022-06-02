package com.ty.zenxl.exception;

/**
 * Raised only if a {@code Code} is not found with the mentioned code type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CodeNotFoundException extends RuntimeException {
	
	public CodeNotFoundException(String message) {
		super(message);
	}
}
