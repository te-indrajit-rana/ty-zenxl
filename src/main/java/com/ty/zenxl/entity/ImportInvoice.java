package com.ty.zenxl.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "import_invoice_details")
public class ImportInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id", unique = true, nullable = false, precision = 10)
	private Integer invoiceId;
	@Column(name = "invoice_num", unique = true)
	private Long invoiceNumber;
	@Column(name = "invoice_date")
	private Date invoiceDate;
	@Column(name = "ship_date")
	private Date shipmentDate;
	@Column(length = 45)
	private String remarks;

//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "pti_status", referencedColumnName = "id")
//	private Status ptiStatus;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "exporter", referencedColumnName = "exporter_id")
	private ExporterDetails exporterDetails;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "importer", referencedColumnName = "importer_id")
	private ImporterDetails importerDetails;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "shipment", referencedColumnName = "shipment_id")
	private ShipmentDetails shipmentDetails;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "invoice")
	private Set<ImportDocument> importDocumentList;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "importInvoice")
	private Set<ShipmentItem> shipmentItemList;

}
