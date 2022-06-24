package com.ty.zenxl.exception;

/**
 * Raised only if a {@code CertificateType} is not found with the mentioned certificate type.
 * 
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CertificateTypeNotFoundException extends RuntimeException {
	
	public CertificateTypeNotFoundException(String message) {
		super(message);
	}
}
