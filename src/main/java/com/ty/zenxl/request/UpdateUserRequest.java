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
 * Contains bean validation properties, to validate the request object using {@code @Valid} 
 * in {@code ZenxlAuthController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

	@Size(min = 2, max = 20)
	private String username;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Past
	private Date dateOfBirth;
	@NotBlank
	private String gender;
	@NotNull
	private String role;
}
