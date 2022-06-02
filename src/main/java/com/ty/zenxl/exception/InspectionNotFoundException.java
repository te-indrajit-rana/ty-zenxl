package com.ty.zenxl.exception;

/**
 * Raised only if a {@code Inspection} is not found with the mentioned inspection type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class InspectionNotFoundException extends RuntimeException {
	
	public InspectionNotFoundException(String message) {
		super(message);
	}
}
