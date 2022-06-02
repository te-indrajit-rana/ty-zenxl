package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Status} is not able to update in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ChangeStatusException extends RuntimeException {

	public ChangeStatusException(String message) {
		super(message);
	}

}
