package com.ty.zenxl.exception;

/**
 * Raised only when an email is not be sent to an user.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class EmailInterruptionException extends RuntimeException {

	public EmailInterruptionException(String message) {
		super(message);
	}
}
