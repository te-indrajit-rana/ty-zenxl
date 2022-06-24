package com.ty.zenxl.request;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
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
public class ImportInvoiceRequest {

	@NotNull(message = "invoiceNumber cannot be null")
	private Long invoiceNumber;
	@NotNull(message = "invoiceDate cannot be null")
	@FutureOrPresent(message = "invoiceDate cannot be a past date")
	private Date invoiceDate;
	@NotNull(message = "shipmentDate cannot be null")
	@FutureOrPresent(message = "shipmentDate cannot be a past date")
	private Date shipmentDate;
	@NotNull(message = "exporterName cannot be null")
	@NotBlank(message = "exporterName cannot be blank")
	private String exporterName;
	@NotNull(message = "shipperName cannot be null")
	@NotBlank(message = "shipperName cannot be blank")
	private String shipperName;
	@NotNull(message = "countryOfExport cannot be null")
	@NotBlank(message = "countryOfExport cannot be blank")
	private String countryOfExport;
	@NotNull(message = "importerName cannot be null")
	@NotBlank(message = "importerName cannot be blank")
	private String importerName;
	@NotNull(message = "consigneeName cannot be null")
	@NotBlank(message = "consigneeName cannot be blank")
	private String consigneeName;
	@NotNull(message = "countryOfImport cannot be null")
	@NotBlank(message = "countryOfImport cannot be blank")
	private String countryOfImport;
	@NotNull(message = "incotermType cannot be null")
	@NotBlank(message = "incotermType cannot be blank")
	private String incotermType;
	@NotNull(message = "modeOfTransport cannot be null")
	@NotBlank(message = "modeOfTransport cannot be blank")
	private String modeOfTransport;
	@NotNull(message = "shipVia cannot be null")
	@NotBlank(message = "shipVia cannot be blank")
	private String shipVia;
	@NotNull(message = "currency cannot be null")
	@NotBlank(message = "currency cannot be blank")
	private String currency;
	@NotNull(message = "packageNumber cannot be null")
	private Integer packageNumber;
	@NotNull(message = "grossWeight cannot be null")
	private Double grossWeight;
	@NotNull(message = "wayBillNumber cannot be null")
	@NotBlank(message = "wayBillNumber cannot be blank")
	private String wayBillNumber;
	@NotNull(message = "remarks cannot be null")
	@NotBlank(message = "remarks cannot be blank")
	private String remarks;
	@NotNull(message = "internalOrderNumber cannot be null")
	private Long internalOrderNumber;
	@Valid
	@NotNull(message = "itemRequest cannot be null")
	private ItemRequest itemRequest;
	@Valid
	@NotNull(message = "inspectionList cannot be null")
	private List<InspectionRequest> inspectionList;

}
