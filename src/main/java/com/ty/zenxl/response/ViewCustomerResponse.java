package com.ty.zenxl.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response DTO to view the {@code Customer}
 * also include the null valued properties. 
 *  
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ViewCustomerResponse {
	
	private String customerName;
	private AddressResponse customerAddressResponse;
	private String contactPerson;
	private long phoneNumber;
	private String email;
	private BillingDetailsResponse billingDetailsResponse;
}
