package com.ty.zenxl.exception;

/**
 * Raised only when {@code User} is not valid to be authenticated.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class LoginException extends RuntimeException {

	public LoginException(String message) {
		super(message);
	}
}
