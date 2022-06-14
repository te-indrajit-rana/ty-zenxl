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

	@NotNull
	@NotBlank
	private String customerName;
	@NotNull
	@NotBlank
	private String contactPerson;
	@NotNull
	private Long phoneNumber;
	@Email
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	private AddressRequest addressRequest;
	@NotNull
	private BillingDetailsRequest billingDetailsRequest;
}
