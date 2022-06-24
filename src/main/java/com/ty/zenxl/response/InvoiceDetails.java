package com.ty.zenxl.response;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDetails {

	private Long invoiceNumber;

	private Date invoiceDate;

	private Date shipmentDate;

	// private Status ptiStatus;

	private String exporterName;

	private String shipperName;

	private String countryOfExport;

	private String importerName;

	private String consigneeName;

	private String countryOfImport;

	private String incotermType;

	private String modeOfTransport;

	private String shipVia;

	private String currency;

	private Integer packageNumber;

	private Double grossWeight;

	private String wayBillNumber;

	private String remarks;

	private Set<ShipmentItemInfo> shipmentItemList;

}
