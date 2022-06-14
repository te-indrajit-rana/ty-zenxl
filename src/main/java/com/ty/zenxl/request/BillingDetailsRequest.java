package com.ty.zenxl.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code BillingDetails}
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
public class BillingDetailsRequest {

	@NotNull
	@NotBlank
	private String billingName;
	@NotNull
	private Long billingContactNumber;
	@Email
	@NotNull
	@NotBlank
	private String billingEmail;;
	@NotNull
	private AddressRequest addressRequest;

}
