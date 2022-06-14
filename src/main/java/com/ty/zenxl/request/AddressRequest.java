package com.ty.zenxl.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Address}
 * 
 * Contains bean validation properties, to validate the request object using
 * {@code @Valid} in {@code ZenxlCustomerController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

	@NotNull
	@NotBlank
	private String address1;
	@NotNull
	@NotBlank
	private String address2;
	@NotNull
	@NotBlank
	private String city;
	@NotNull
	@NotBlank
	private String state;
	@NotNull
	private Long zipCode;
	@NotNull
	@NotBlank
	private String country;
}
