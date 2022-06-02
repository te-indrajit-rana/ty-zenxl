package com.ty.zenxl.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Customer} for update purpose.
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
public class UpdateCustomerRequest {

	@NotNull
	private String customerName;
	@NotNull
	private String contactPerson;

	private long phoneNumber;

	@Email
	@NotNull
	private String email;

	@NotNull
	private AddressRequest addressRequest;
	@NotNull
	private BillingDetailsRequest billingDetailsRequest;
}
