package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Inspection} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class InspectionPersistenceException extends RuntimeException {

	public InspectionPersistenceException(String message) {
		super(message);
	}
}
