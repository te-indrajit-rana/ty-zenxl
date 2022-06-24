package com.ty.zenxl.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentItemInfo {

	private Long partNumber;

	private Long serialNumber;

	private Integer quantity;

	private String description;

	// private String hsCode;

	// private String eccnCode;

}