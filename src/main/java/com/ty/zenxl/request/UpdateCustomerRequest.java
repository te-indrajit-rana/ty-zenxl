package com.ty.zenxl.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Customer} for update purpose.
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
public class UpdateCustomerRequest {

	@NotNull(message = "Customer name must not be null.")
	@NotBlank(message = "Customer name must not be blank.")
	private String customerName;
	@NotNull(message = "Contact person must not be null.")
	@NotBlank(message = "Contact person must not be blank.")
	private String contactPerson;
	@NotNull(message = "Phone number must not be null.")
	private Long phoneNumber;
	@Email(message = "Please provide a valid email.")
	@NotNull(message = "Email must not be null.")
	@NotBlank(message = "Email must not be blank.")
	private String email;
	@NotNull(message = "Address must not be null.")
	private AddressRequest addressRequest;
	@NotNull(message = "Billing details must not be null.")
	private BillingDetailsRequest billingDetailsRequest;
}
