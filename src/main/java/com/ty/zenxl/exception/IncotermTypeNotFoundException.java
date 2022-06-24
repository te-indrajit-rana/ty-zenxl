package com.ty.zenxl.exception;

/**
 * Raised only if a {@code IncotermType} is not found with the mentioned incoterm type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class IncotermTypeNotFoundException extends RuntimeException {
	
	public IncotermTypeNotFoundException(String message) {
		super(message);
	}
}
