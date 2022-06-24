package com.ty.zenxl.request;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillOfEntryRequest {

	@NotNull(message = "Port code cannot be null")
	@NotEmpty(message = "Port code cannot be empty")
	@NotBlank(message = "Port code cannot be blank")
	private String portCode;
	@NotNull(message = "BOE Number cannot be null")
	@Positive(message = "BOE Number cannot be a negative number")
	private Long boeNumber;
	private Date boeDate;
	private BigDecimal grossWeight;
	private String cHAgent;
	private BigDecimal bcd;
	private BigDecimal acd;
	private BigDecimal sws;
	private BigDecimal nccd;
	private BigDecimal add;
	private BigDecimal cvd;
	private BigDecimal igst;
	private BigDecimal cess;
	private BigDecimal assessedvalue;
	private BigDecimal totalDuty;
	private BigDecimal iNT;
	private BigDecimal pnlty;
	private BigDecimal fine;
	private BigDecimal totalDutyAmount;

}
