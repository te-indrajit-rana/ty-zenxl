package com.ty.zenxl.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "shipment_details")
public class ShipmentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipment_id", unique = true, nullable = false, precision = 10)
	private Integer shipmentId;
	@Column(name = "mode_of_transport", length = 45)
	private String modeOfTransport;
	@Column(length = 45)
	private String shipVia;
	@Column(length = 45)
	private String currency;
	@Column(name = "package_num")
	private Integer packageNumber;
	@Column(name = "gross_weight")
	private Double grossWeight;
	@Column(name = "way_bill", unique = true, length = 45)
	private String wayBillNumber;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "incoterm", referencedColumnName = "incoterm_type_id")
	private IncotermType incotermType;

}
