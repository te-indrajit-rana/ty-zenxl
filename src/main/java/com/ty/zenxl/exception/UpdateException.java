package com.ty.zenxl.exception;

/**
 * Raised only when there is a failure in update operation.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class UpdateException extends RuntimeException {

	public UpdateException(String message) {
		super(message);
	}
}
