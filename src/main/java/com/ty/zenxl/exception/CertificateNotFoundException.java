package com.ty.zenxl.exception;

/**
 * Raised only if a {@code Certificate} is not found with the mentioned certificate type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CertificateNotFoundException extends RuntimeException {
	
	public CertificateNotFoundException(String message) {
		super(message);
	}
}
