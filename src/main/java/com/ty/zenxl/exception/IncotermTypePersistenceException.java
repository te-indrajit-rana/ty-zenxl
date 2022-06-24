package com.ty.zenxl.exception;

/**
 * Raised only when a {@code IncotermType} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class IncotermTypePersistenceException extends RuntimeException {
	
	public IncotermTypePersistenceException(String message) {
		super(message);
	}
}
