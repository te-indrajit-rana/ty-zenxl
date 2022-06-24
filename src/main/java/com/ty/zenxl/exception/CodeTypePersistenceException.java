package com.ty.zenxl.exception;

/**
 * Raised only when a {@code CodeType} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CodeTypePersistenceException extends RuntimeException {

	public CodeTypePersistenceException(String message) {
		super(message);
	}
}
