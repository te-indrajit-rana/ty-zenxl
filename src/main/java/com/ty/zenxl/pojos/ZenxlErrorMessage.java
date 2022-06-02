package com.ty.zenxl.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the standard error format used throughout the application.
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZenxlErrorMessage {

	private boolean isError;
	private String message;

}
