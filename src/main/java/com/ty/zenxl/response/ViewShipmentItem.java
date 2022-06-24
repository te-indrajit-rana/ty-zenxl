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
public class ViewShipmentItem {
	
	private Long partNumber;
	
	private Long internalOrderNumber;
	
	private Long serialNumber;
	
	private String manufacturerName;
	
	private String description;
	
	private Integer quantity;
	
	private String countryOfOrigin;
	
	private String unitOfMeasure;
	
	private Double unitPrice;
	
	private Double amount;
	
	private Set<CodeResponse> codeList;

	private Set<ViewCertificateList> certificateList;

}
