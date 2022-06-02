package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Passcode} is not able to update in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ChangePasswordException extends RuntimeException {

	public ChangePasswordException(String message) {
		super(message);
	}

}
