package com.ty.zenxl.exception;

/**
 * Raised only if a {@code InspectionType} is not found with the mentioned inspection type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class InspectionTypeNotFoundException extends RuntimeException {
	
	public InspectionTypeNotFoundException(String message) {
		super(message);
	}
}
