package com.ty.zenxl.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Address}
 * 
 * Contains bean validation properties, to validate the request object using {@code @Valid} 
 * in {@code ZenxlCustomerController}
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
	private String address1;
	@NotNull
	private String address2;
	@NotNull
	private String city;
	@NotNull
	private String state;
	private long zipCode;
	@NotNull
	private String country;
}
