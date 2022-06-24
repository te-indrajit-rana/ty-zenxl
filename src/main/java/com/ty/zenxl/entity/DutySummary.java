package com.ty.zenxl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "duty_summary")
public class DutySummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "duty_summary_id", unique = true, nullable = false, precision = 10)
	private Integer dutySummaryId;
	@Column
	private BigDecimal bcd;
	@Column
	private BigDecimal acd;
	@Column
	private BigDecimal sws;
	@Column
	private BigDecimal nccd;
	@Column(name = "_add")
	private BigDecimal add;
	@Column
	private BigDecimal cvd;
	@Column
	private BigDecimal igst;
	@Column
	private BigDecimal cess;
	@Column
	private BigDecimal assessedvalue;
	@Column
	private BigDecimal totalDuty;
	@Column(name = "_int")
	private BigDecimal iNT;
	@Column
	private BigDecimal pnlty;
	@Column
	private BigDecimal fine;
	@Column
	private BigDecimal totalDutyAmount;

}
