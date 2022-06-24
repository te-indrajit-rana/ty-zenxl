package com.ty.zenxl.exception;

/**
 * Raised only when a {@code CertificateType} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CertificateTypePersistenceException extends RuntimeException {

	public CertificateTypePersistenceException(String message) {
		super(message);
	}
}
