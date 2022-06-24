package com.ty.zenxl.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DutySummaryRequest {

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
