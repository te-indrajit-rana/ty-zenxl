package com.ty.zenxl.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDashboardResponse {

	private Boolean isError;
	private String message;
	private List<Item> itemsList;

}
