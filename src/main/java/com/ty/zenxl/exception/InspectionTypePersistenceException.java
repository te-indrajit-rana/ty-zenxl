package com.ty.zenxl.exception;

/**
 * Raised only when a {@code InspectionType} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class InspectionTypePersistenceException extends RuntimeException {

	public InspectionTypePersistenceException(String message) {
		super(message);
	}
}
