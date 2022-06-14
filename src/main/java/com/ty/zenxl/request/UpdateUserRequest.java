package com.ty.zenxl.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code User} for update purpose.
 * 
 * Contains bean validation properties, to validate the request object using
 * {@code @Valid} in {@code ZenxlAuthController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

	@Size(min = 2, max = 20, message = "Username must be in between 2 to 20 characters.")
	private String username;
	@Email(message = "Please provide a valid email.")
	@NotNull(message = "Email must not be null.")
	@NotBlank(message = "Email must not be blank.")
	private String email;
	@Past(message = "Date of birth must be in the past.")
	@NotNull(message = "Date of birth must not be null.")
	private Date dateOfBirth;
	@NotNull(message = "Gender must not be null.")
	@NotBlank(message = "Gender must not be blank.")
	private String gender;
	@NotNull(message = "Role must not be null.")
	@NotBlank(message = "Role must not be blank.")
	private String role;
}
