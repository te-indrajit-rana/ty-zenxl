package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Status} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class StatusPersistenceException extends RuntimeException {

	public StatusPersistenceException(String message) {
		super(message);
	}
}
