package com.ty.zenxl.request;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
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
public class CertificateUpdateRequest {

	@NotNull(message = "certificateNumber cannot be null")
	@NotBlank(message = "certificateNumber cannot be blank")
	private String certificateNumber;

	@NotNull(message = "expiryDate cannot be null")
	@FutureOrPresent(message = "expiryDate must be a present or future date")
	private Date expiryDate;

}
