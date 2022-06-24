package com.ty.zenxl.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeRequest {

	@NotNull(message = "codeType cannot be null")
	@NotBlank(message = "codeType cannot be blank")
	private String codeType;
	@NotNull(message = "codeValue cannot be null")
	@NotBlank(message = "codeValue cannot be blank")
	private String codeValue;

}