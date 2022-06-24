package com.ty.zenxl.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class ItemUpdateRequest {

	@NotNull(message = "customerName cannot be null")
	@NotBlank(message = "customerName cannot be blank")
	private String customerName;
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
	@NotNull(message = "countryOfOrigin cannot be null")
	@NotBlank(message = "countryOfOrigin cannot be blank")
	@Size(min = 3, max = 20, message = "countryOfOrigin size must lie between 3 and 20")
	private String countryOfOrigin;
	@Valid
	@NotNull(message = "codeRequestList cannot be null")
	@NotEmpty(message = "codeRequestList cannot be empty")
	private List<CodeRequest> codeRequestList;
}