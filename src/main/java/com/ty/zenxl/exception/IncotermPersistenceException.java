package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Incoterm} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class IncotermPersistenceException extends RuntimeException {
	
	public IncotermPersistenceException(String message) {
		super(message);
	}
}
