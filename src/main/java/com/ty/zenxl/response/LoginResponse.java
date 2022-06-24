package com.ty.zenxl.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response DTO for successful login response 
 * along with the {@code JwtToken} generated.
 *  
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class LoginResponse {

	private Boolean isError;
	private String message;
	private JwtToken data;
	
	public LoginResponse(Boolean isError, String message) {
		this.isError = isError;
		this.message = message;
	}
}
