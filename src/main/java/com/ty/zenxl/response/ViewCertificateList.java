package com.ty.zenxl.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewCertificateList {

	private String certificateNumber;

	private Date expiryDate;

	private String certificateType;

}
