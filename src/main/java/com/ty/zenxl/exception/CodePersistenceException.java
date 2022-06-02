package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Code} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CodePersistenceException extends RuntimeException {

	public CodePersistenceException(String message) {
		super(message);
	}
}
