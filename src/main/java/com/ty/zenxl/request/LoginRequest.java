package com.ty.zenxl.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code User} for login purpose.
 * 
 * Contains bean validation properties, to validate the request object using
 * {@code @Valid} in {@code ZenxlAuthController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

	@Email(message = "Please provide a valid email.")
	@NotNull(message = "Email must not be null.")
	@NotBlank(message = "Email must not be blank.")
	private String email;
	@NotNull(message = "Password must not be null.")
	@NotBlank(message = "Password must not be blank.")
	private String password;

}
