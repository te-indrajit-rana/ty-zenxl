package com.ty.zenxl.exception;

/**
 * Raised only if a {@code HsCode} is not found with the mentioned hsCode type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class HsCodeNotFoundException extends RuntimeException {

	public HsCodeNotFoundException(String message) {
		super(message);
	}
}
