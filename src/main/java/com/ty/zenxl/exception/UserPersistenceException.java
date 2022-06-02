package com.ty.zenxl.exception;

/**
 * Raised only when a {@code User} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class UserPersistenceException extends RuntimeException {

	public UserPersistenceException(String message) {
		super(message);
	}
}
