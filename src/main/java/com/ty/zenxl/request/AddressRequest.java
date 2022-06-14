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

	@NotNull(message = "Address1 must not be null.")
	@NotBlank(message = "Address1 must not be blank.")
	private String address1;
	@NotNull(message = "Address2 must not be null.")
	@NotBlank(message = "Address2 must not be blank.")
	private String address2;
	@NotNull(message = "City name must not be null.")
	@NotBlank(message = "City name must not be blank.")
	private String city;
	@NotNull(message = "State name must not be null.")
	@NotBlank(message = "State name must not be blank.")
	private String state;
	@NotNull(message = "Zipcode must not be null.")
	private Long zipCode;
	@NotNull(message = "Country name must not be null.")
	@NotBlank(message = "Country name must not be blank.")
	private String country;
}
