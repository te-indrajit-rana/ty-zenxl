package com.ty.zenxl.exception;

/**
 * Raised only if a {@code Incoterm} is not found with the mentioned incoterm type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class IncotermNotFoundException extends RuntimeException {
	
	public IncotermNotFoundException(String message) {
		super(message);
	}
}
