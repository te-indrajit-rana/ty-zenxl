package com.ty.zenxl.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Passcode}
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
public class ChangePasswordRequest {

	@NotNull(message = "Passcode must not be null.")
	private Integer passcode;
	@Size(min = 4, max = 16, message = "Password must be within 4 to 16 characters.")
	private String password;
	@Size(min = 4, max = 16, message = "Confirm Password must be within 4 to 16 characters.")
	private String confirmPassword;
}
