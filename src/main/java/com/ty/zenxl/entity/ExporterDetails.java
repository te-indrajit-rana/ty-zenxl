package com.ty.zenxl.entity;

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
@Table(name = "exporter_details")
public class ExporterDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exporter_id", unique = true, nullable = false, precision = 10)
	private Integer exportId;
	@Column(name = "exporter_name", length = 45)
	private String exporterName;
	@Column(name = "shipper_name", length = 45)
	private String shipperName;
	@Column(name = "country_of_export", length = 45)
	private String countryOfExport;

}
