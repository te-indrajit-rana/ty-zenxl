package com.ty.zenxl.exception;

/**
 * Raised only if a {@code CodeType} is not found with the mentioned code type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CodeTypeNotFoundException extends RuntimeException {
	
	public CodeTypeNotFoundException(String message) {
		super(message);
	}
}
