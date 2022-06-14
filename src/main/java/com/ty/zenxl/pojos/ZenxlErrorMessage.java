package com.ty.zenxl.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_DEFAULT)
public class ZenxlErrorMessage {

	private Boolean isError;
	private String message;

}
