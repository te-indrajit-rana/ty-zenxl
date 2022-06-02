package com.ty.zenxl.exception;

/**
 * Raised only when a {@code Certificate} is not able to persist in the database.
 *
 * Handled in {@code ZenxlExceptionHandler} class.
 * 
 * @author Indrajit
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CertificatePersistenceException extends RuntimeException {

	public CertificatePersistenceException(String message) {
		super(message);
	}
}
