package com.ty.zenxl.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceListResponse {

	private Boolean isError;
	private String message;
	private Set<InvoiceDetails> listOInvoiceDetails;

}
