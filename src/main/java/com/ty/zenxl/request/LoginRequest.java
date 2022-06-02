package com.ty.zenxl.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code User} for login purpose.
 * 
 * Contains bean validation properties, to validate the request object using {@code @Valid} 
 * in {@code ZenxlAuthController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	
}
