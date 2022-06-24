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
public class Item {

	private Long serialNumber;

	private String manufacturerName;

	private Long partNumber;

	private Integer quantity;

	private String description;

	private String unitOfMeasure;

	private Double unitPrice;

	private String countryOfOrigin;

	private Set<CodeResponse> codeList;

	private Set<ViewCertificateList> certificateList;

}
