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

	@NotNull(message = "Billing name must not be null.")
	@NotBlank(message = "Billing name must not be blank.")
	private String billingName;
	@NotNull(message = "Billing contact number must not be null.")
	private Long billingContactNumber;
	@Email(message = "Please provide a valid email.")
	@NotNull(message = "Billing email must not be null.")
	@NotBlank(message = "Billing email must not be blank.")
	private String billingEmail;;
	@NotNull(message = "Billing Address must not be null.")
	private AddressRequest addressRequest;

}
