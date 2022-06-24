package com.ty.zenxl.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
@Table(name = "inspection_details")
public class Inspection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inspection_id", unique = true, nullable = false, precision = 10)
	private Integer inspectionId;
	@Column(name = "inspection_value", length = 10)
	private String inspectionValue;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "i_type", joinColumns = @JoinColumn(name = "ins_id", referencedColumnName = "inspection_id"), inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "inspection_type_id"))
	private InspectionType inspectionType;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "shipment_item_id")
	private ShipmentItem shipmentItem;

}
