package com.ty.zenxl.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentRequest {

	@NotNull(message = "internalOrderNumber cannot be null")
	private Long internalOrderNumber;
	@Valid
	@NotNull(message = "itemRequest cannot be null")
	private ItemRequest itemRequest;
	@Valid
	@NotNull(message = "inspectionList cannot be null")
	private List<InspectionRequest> inspectionList;

}
