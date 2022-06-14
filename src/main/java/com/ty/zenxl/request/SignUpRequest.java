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
 * Represents the request DTO for {@code User} for signup purpose.
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
public class SignUpRequest {

	@Size(min = 2, max = 20)
	private String username;
	@Email
	@NotNull
	@NotBlank
	private String email;
	@Past
	@NotNull
	private Date dateOfBirth;
	@NotNull
	@NotBlank
	private String gender;
	@NotNull
	@NotBlank
	private String role;
	@Size(min = 4, max = 16)
	private String password;

}
