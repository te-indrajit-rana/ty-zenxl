package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Customer} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CustomerPersistenceException extends RuntimeException {

	public CustomerPersistenceException(String message) {
		super(message);
	}
}
