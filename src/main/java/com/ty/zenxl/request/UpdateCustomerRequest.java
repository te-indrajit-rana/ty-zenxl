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
	
	
	@NotNull(message = "Billing name must not be null.")
	@NotBlank(message = "Billing name must not be blank.")
	private String billingName;
	@NotNull(message = "Billing contact number must not be null.")
	private Long billingContactNumber;
	@Email(message = "Please provide a valid email.")
	@NotNull(message = "Billing email must not be null.")
	@NotBlank(message = "Billing email must not be blank.")
	private String billingEmail;
	
	@NotNull(message = "Billing address1 must not be null.")
	@NotBlank(message = "Billing address1 must not be blank.")
	private String billingAddress1;
	@NotNull(message = "Billing address2 must not be null.")
	@NotBlank(message = "Billing address2 must not be blank.")
	private String billingAddress2;
	@NotNull(message = "Billing city name must not be null.")
	@NotBlank(message = "Billing city name must not be blank.")
	private String billingCity;
	@NotNull(message = "Billing state name must not be null.")
	@NotBlank(message = "Billing state name must not be blank.")
	private String billingState;
	@NotNull(message = "Billing zipcode must not be null.")
	private Long billingZipCode;
	@NotNull(message = "Billing country name must not be null.")
	@NotBlank(message = "Billing country name must not be blank.")
	private String billingCountry;
}
