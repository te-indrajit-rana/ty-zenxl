package com.ty.zenxl.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {

	@NotNull(message = "serialNumber cannot be null")
	private Long serialNumber;
	@NotNull(message = "manufacturerName cannot be null")
	@NotBlank(message = "manufacturerName cannot be blank")
	private String manufacturerName;
	@NotNull(message = "partNumber cannot be null")
	private Long partNumber;
	@Positive(message = "quantity must be greater than zero")
	@NotNull(message = "quantity cannot be null")
	private Integer quantity;
	@NotNull(message = "description cannot be null")
	@NotBlank(message = "description cannot be blank")
	private String description;
	@NotNull(message = "unitOfMeasure cannot be null")
	@NotBlank(message = "unitOfMeasure cannot be blank")
	private String unitOfMeasure;
	@Positive(message = "unitPrice must be greater than zero")
	@NotNull(message = "unitPrice cannot be null")
	private Double unitPrice;
	@NotNull(message = "amount cannot be null")
	@Positive(message = "amount should be greater than 0")
	private Double amount;
	@NotNull(message = "countryOfOrigin cannot be null")
	@NotBlank(message = "countryOfOrigin cannot be blank")
	@Size(min = 3, max = 20, message = "countryOfOrigin size must lie between 3 and 20")
	private String countryOfOrigin;
	@Valid
	@NotNull(message = "codeRequestList cannot be null")
	private List<CodeRequest> codeRequestList;

}
