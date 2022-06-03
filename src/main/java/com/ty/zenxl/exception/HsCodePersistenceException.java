package com.ty.zenxl.exception;

/**
 * Raised only when a {@code HsCode} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class HsCodePersistenceException extends RuntimeException {
	
	public HsCodePersistenceException(String message) {
		super(message);
	}
}
