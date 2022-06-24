package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.BillOfEntry;
import com.ty.zenxl.entity.Code;
import com.ty.zenxl.entity.CodeType;
import com.ty.zenxl.entity.DutySummary;
import com.ty.zenxl.entity.ExportInvoice;
import com.ty.zenxl.entity.ExporterDetails;
import com.ty.zenxl.entity.ImporterDetails;
import com.ty.zenxl.entity.IncotermType;
import com.ty.zenxl.entity.Inspection;
import com.ty.zenxl.entity.InspectionType;
import com.ty.zenxl.entity.ItemDetails;
import com.ty.zenxl.entity.ShipmentDetails;
import com.ty.zenxl.entity.ShipmentItem;
import com.ty.zenxl.exception.BillOfEntryException;
import com.ty.zenxl.exception.InvoiceException;
import com.ty.zenxl.exception.ItemException;
import com.ty.zenxl.exception.ShipmentDetailsException;
import com.ty.zenxl.exception.ShipmentItemException;
import com.ty.zenxl.repository.BillOfEntryRepository;
import com.ty.zenxl.repository.CodeTypeRepository;
import com.ty.zenxl.repository.ExportInvoiceRepository;
import com.ty.zenxl.repository.IncotermTypeRepository;
import com.ty.zenxl.repository.InspectionTypeRepository;
import com.ty.zenxl.repository.ItemRepository;
import com.ty.zenxl.repository.ShipmentDetailsRepository;
import com.ty.zenxl.repository.ShipmentItemRepository;
import com.ty.zenxl.request.BillOfEntryRequest;
import com.ty.zenxl.request.CodeRequest;
import com.ty.zenxl.request.DutySummaryRequest;
import com.ty.zenxl.request.ExportInvoiceRequest;
import com.ty.zenxl.request.ExportInvoiceUpdate;
import com.ty.zenxl.request.InspectionRequest;
import com.ty.zenxl.request.ShipmentRequest;
import com.ty.zenxl.request.ShipmentUpdateRequest;
import com.ty.zenxl.response.CodeResponse;
import com.ty.zenxl.response.InvoiceDetails;
import com.ty.zenxl.response.InvoiceListResponse;
import com.ty.zenxl.response.InvoiceResponse;
import com.ty.zenxl.response.ShipmentItemInfo;
import com.ty.zenxl.response.ViewCertificateList;
import com.ty.zenxl.response.ViewShipmentItem;
import com.ty.zenxl.response.ZenxlResponseBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ZenxlExportService {

	private final ExportInvoiceRepository invoiceRepository;

	private final ShipmentItemRepository shipmentItemRepository;

	private final CodeTypeRepository codeTypeRepository;

	private final ItemRepository itemRepository;

	private final ShipmentDetailsRepository shipmentDetailsRepository;

	private final IncotermTypeRepository incotermTypeRepository;

	private final InspectionTypeRepository inspectionTypeRepository;

	private final BillOfEntryRepository billOfEntryRepository;

	/**
	 * Add export invoice.
	 * 
	 * @param request
	 * @return {@link InvoiceResponse}
	 */
	public InvoiceResponse addInvoice(ExportInvoiceRequest request) {

		if (Boolean.TRUE.equals(invoiceRepository.existsByInvoiceNumber(request.getInvoiceNumber())))
			throw new InvoiceException(INVOICE_ALREADY_EXISTS);

		if (Boolean.TRUE.equals(itemRepository.existsBySerialNumber(request.getItemRequest().getSerialNumber())))
			throw new ItemException(ITEM_ALREADY_EXISTS);

		if (Boolean.TRUE.equals(shipmentDetailsRepository.existsByWayBillNumber(request.getWayBillNumber())))
			throw new ShipmentDetailsException(SHIPMENT_DETAILS_ALREADY_EXISTS);

		if (Boolean.TRUE.equals(shipmentItemRepository.existsByInternalOrderNumber(request.getInternalOrderNumber())))
			throw new ShipmentItemException(SHIPMENT_ITEM_ALREADY_EXISTS);

		ExportInvoice invoice = ExportInvoice.builder().invoiceNumber(request.getInvoiceNumber()).build();

		ShipmentItem shipmentItem = ShipmentItem.builder().internalOrderNumber(request.getInternalOrderNumber())
				.build();
		Set<ShipmentItem> shipmentItemList = new HashSet<>();
		shipmentItemList.add(shipmentItem);

		ItemDetails itemDetails = ItemDetails.builder().serialNumber(request.getItemRequest().getSerialNumber())
				.build();
		Set<Code> codeList = request.getItemRequest().getCodeRequestList().stream().map(codeRequest -> {
			CodeType codeType = codeTypeRepository.findByCodeType(codeRequest.getCodeType())
					.orElse(CodeType.builder().codeType(codeRequest.getCodeType()).build());
			return Code.builder().codeType(codeType).codeValue(codeRequest.getCodeValue()).item(itemDetails).build();
		}).collect(Collectors.toSet());
		itemDetails.setManufacturerName(request.getItemRequest().getManufacturerName());
		itemDetails.setPartNumber(request.getItemRequest().getPartNumber());
		itemDetails.setQuantity(request.getItemRequest().getQuantity());
		itemDetails.setDescription(request.getItemRequest().getDescription());
		itemDetails.setUnitOfMeasure(request.getItemRequest().getUnitOfMeasure());
		itemDetails.setUnitPrice(request.getItemRequest().getUnitPrice());
		itemDetails.setCountryOfOrigin(request.getItemRequest().getCountryOfOrigin());
		itemDetails.setCodeList(codeList);

		Set<Inspection> inspectionList = request.getInspectionList().stream().map(inspectionRequest -> {
			InspectionType inspectionType = inspectionTypeRepository
					.findByInspectionType(inspectionRequest.getInspectionType())
					.orElse(InspectionType.builder().inspectionType(inspectionRequest.getInspectionType()).build());
			return Inspection.builder().inspectionType(inspectionType)
					.inspectionValue(inspectionRequest.getInspectionValue()).shipmentItem(null).build();
		}).collect(Collectors.toSet());

		shipmentItem.setAmount(request.getItemRequest().getAmount());
		shipmentItem.setItemDetails(itemDetails);
		shipmentItem.setInspectionList(inspectionList);

		ExporterDetails exporterDetails = ExporterDetails.builder().exporterName(request.getExporterName())
				.shipperName(request.getShipperName()).countryOfExport(request.getCountryOfExport()).build();

		ImporterDetails importerDetails = ImporterDetails.builder().importerName(request.getImporterName())
				.consigneeName(request.getConsigneeName()).countryOfImport(request.getCountryOfImport()).build();

		IncotermType incotermType = incotermTypeRepository.findByIncotermType(request.getIncotermType())
				.orElse(IncotermType.builder().incotermType(request.getIncotermType()).build());
		ShipmentDetails shipmentDetails = ShipmentDetails.builder().modeOfTransport(request.getModeOfTransport())
				.shipVia(request.getShipVia()).currency(request.getCurrency()).packageNumber(request.getPackageNumber())
				.grossWeight(request.getGrossWeight()).wayBillNumber(request.getWayBillNumber())
				.incotermType(incotermType).build();

		invoice.setCustomerName(request.getCustomerName());
		invoice.setExportReference(request.getExportReference());
		invoice.setInvoiceDate(request.getInvoiceDate());
		invoice.setShipmentDate(request.getShipmentDate());
		invoice.setRemarks(request.getRemarks());
		invoice.setExporterDetails(exporterDetails);
		invoice.setImporterDetails(importerDetails);
		invoice.setShipmentDetails(shipmentDetails);
		invoice.setShipmentItemList(shipmentItemList);
		shipmentItemRepository.save(shipmentItem);
		invoiceRepository.save(invoice);

		return InvoiceResponse.builder().isError(false).message(INVOICE_ADDED_SUCCESSFULLY).build();
	}

	/**
	 * View export invoice by invoice number.
	 * 
	 * @param invoiceNumber
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody viewInvoice(Long invoiceNumber) {

		ExportInvoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new InvoiceException(INVOICE_NOT_FOUND));

		Set<ShipmentItemInfo> shipmentItemItemList = invoice.getShipmentItemList().stream().map(shipment -> {
			return ShipmentItemInfo.builder().partNumber(shipment.getItemDetails().getPartNumber())
					.serialNumber(shipment.getItemDetails().getSerialNumber())
					.quantity(shipment.getItemDetails().getQuantity())
					.description(shipment.getItemDetails().getDescription()).build();
		}).collect(Collectors.toSet());

		InvoiceDetails invoiceDetails = InvoiceDetails.builder().invoiceNumber(invoice.getInvoiceNumber())
				.invoiceDate(invoice.getInvoiceDate()).shipmentDate(invoice.getShipmentDate())
				.exporterName(invoice.getExporterDetails().getExporterName())
				.shipperName(invoice.getExporterDetails().getShipperName())
				.countryOfExport(invoice.getExporterDetails().getCountryOfExport())
				.importerName(invoice.getImporterDetails().getImporterName())
				.consigneeName(invoice.getImporterDetails().getConsigneeName())
				.countryOfImport(invoice.getImporterDetails().getCountryOfImport())
				.incotermType(invoice.getShipmentDetails().getIncotermType().getIncotermType())
				.modeOfTransport(invoice.getShipmentDetails().getModeOfTransport())
				.shipVia(invoice.getShipmentDetails().getShipVia()).currency(invoice.getShipmentDetails().getCurrency())
				.packageNumber(invoice.getShipmentDetails().getPackageNumber())
				.grossWeight(invoice.getShipmentDetails().getGrossWeight())
				.wayBillNumber(invoice.getShipmentDetails().getWayBillNumber()).remarks(invoice.getRemarks())
				.shipmentItemList(shipmentItemItemList).build();

		return ZenxlResponseBody.builder().isError(false).message(ITEM_FETCHED_SUCCESSFULLY).data(invoiceDetails)
				.build();
	}

	/**
	 * view all export invoices
	 * 
	 * @return {@link InvoiceListResponse}
	 */
	public InvoiceListResponse viewAllInvoices() {

		List<ExportInvoice> invoiceList = invoiceRepository.findAll();
		if (invoiceList.isEmpty())
			throw new InvoiceException(INVOICE_NOT_FOUND);

		Set<InvoiceDetails> invoiceDetails = invoiceList.stream().map(invoice -> {
			Set<ShipmentItemInfo> shipmentItemInfo = invoice.getShipmentItemList().stream().map(shipmentItem ->

			ShipmentItemInfo.builder().partNumber(shipmentItem.getItemDetails().getPartNumber())
					.serialNumber(shipmentItem.getItemDetails().getSerialNumber())
					.quantity(shipmentItem.getItemDetails().getQuantity())
					.description(shipmentItem.getItemDetails().getDescription()).build()).collect(Collectors.toSet());

			return InvoiceDetails.builder().invoiceNumber(invoice.getInvoiceNumber())
					.invoiceDate(invoice.getInvoiceDate()).shipmentDate(invoice.getShipmentDate())
					.exporterName(invoice.getExporterDetails().getExporterName())
					.shipperName(invoice.getExporterDetails().getShipperName())
					.countryOfExport(invoice.getExporterDetails().getCountryOfExport())
					.importerName(invoice.getImporterDetails().getImporterName())
					.consigneeName(invoice.getImporterDetails().getConsigneeName())
					.countryOfImport(invoice.getImporterDetails().getCountryOfImport())
					.incotermType(invoice.getShipmentDetails().getIncotermType().getIncotermType())
					.modeOfTransport(invoice.getShipmentDetails().getModeOfTransport())
					.shipVia(invoice.getShipmentDetails().getShipVia())
					.currency(invoice.getShipmentDetails().getCurrency())
					.packageNumber(invoice.getShipmentDetails().getPackageNumber())
					.grossWeight(invoice.getShipmentDetails().getGrossWeight())
					.wayBillNumber(invoice.getShipmentDetails().getWayBillNumber()).remarks(invoice.getRemarks())
					.shipmentItemList(shipmentItemInfo).build();
		}).collect(Collectors.toSet());
		return InvoiceListResponse.builder().isError(false).message(INVOICE_LIST_FETCHED_SUCCESSFULLY)
				.listOInvoiceDetails(invoiceDetails).build();
	}

	/**
	 * Delete export invoice by invoice number.
	 * 
	 * @param invoiceNumber
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody deleteInvoice(Long invoiceNumber) {

		ExportInvoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new InvoiceException(INVOICE_NOT_FOUND));

		invoiceRepository.delete(invoice);

		return ZenxlResponseBody.builder().isError(false).message(INVOICE_DELETED_SUCCESSFULLY).build();
	}

	/**
	 * Update the export invoice.
	 * 
	 * @param invoiceNumber
	 * @param request
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody updateInvoice(Long invoiceNumber, ExportInvoiceUpdate request) {

		ExportInvoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new InvoiceException(INVOICE_NOT_FOUND));

		if (!request.getWayBillNumber().equals(invoice.getShipmentDetails().getWayBillNumber())) {
			if (Boolean.TRUE.equals(shipmentDetailsRepository.existsByWayBillNumber(request.getWayBillNumber()))) {
				throw new InvoiceException(REQUESTED_WAYBILL_NUMBER_ALREADY_EXISTS);
			}
		} else {

			Optional<ShipmentItem> findAnyByInternalOrderNumber = invoice.getShipmentItemList().stream().filter(
					shipmentItem -> shipmentItem.getInternalOrderNumber().equals(request.getInternalOrderNumber()))
					.findAny();
			if (findAnyByInternalOrderNumber.isPresent()) {
				throw new InvoiceException(REQUESTED_INTERNAL_ORDER_NUMBER_IS_ALREADY_EXISTING);
			}

			Optional<Boolean> findAnyBySerialNumber = invoice.getShipmentItemList().stream()
					.map(shipmentItem -> Objects.equals(shipmentItem.getItemDetails().getSerialNumber(),
							request.getItemRequest().getSerialNumber()))
					.findAny();

			if (findAnyBySerialNumber.isPresent()) {
				throw new InvoiceException(REQUESTED_SERIAL_NUMBER_IS_ALREADY_EXISTING);
			}

			ExporterDetails exporterDetails = ExporterDetails.builder().exporterName(request.getExporterName())
					.shipperName(request.getShipperName()).countryOfExport(request.getCountryOfExport()).build();

			ImporterDetails importerDetails = ImporterDetails.builder().importerName(request.getImporterName())
					.consigneeName(request.getConsigneeName()).countryOfImport(request.getCountryOfImport()).build();

			IncotermType incotermType = incotermTypeRepository.findByIncotermType(request.getIncotermType())
					.orElse(IncotermType.builder().incotermType(request.getIncotermType()).build());

			ShipmentDetails shipmentDetail = shipmentDetailsRepository.findByWayBillNumber(request.getWayBillNumber())
					.get();

			shipmentDetail.setModeOfTransport(request.getModeOfTransport());
			shipmentDetail.setShipVia(request.getShipVia());
			shipmentDetail.setCurrency(request.getCurrency());
			shipmentDetail.setPackageNumber(request.getPackageNumber());
			shipmentDetail.setGrossWeight(request.getGrossWeight());
			shipmentDetail.setIncotermType(incotermType);

			ShipmentItem shipmentItem = ShipmentItem.builder().internalOrderNumber(request.getInternalOrderNumber())
					.build();
			Set<ShipmentItem> shipmentItemList = new HashSet<>();
			shipmentItemList.add(shipmentItem);

			ItemDetails itemDetails = ItemDetails.builder().serialNumber(request.getItemRequest().getSerialNumber())
					.build();
			Set<Code> codeList = request.getItemRequest().getCodeRequestList().stream().map(codeRequest -> {
				CodeType codeType = codeTypeRepository.findByCodeType(codeRequest.getCodeType())
						.orElse(CodeType.builder().codeType(codeRequest.getCodeType()).build());
				return Code.builder().codeType(codeType).codeValue(codeRequest.getCodeValue()).item(itemDetails)
						.build();
			}).collect(Collectors.toSet());
			itemDetails.setManufacturerName(request.getItemRequest().getManufacturerName());
			itemDetails.setPartNumber(request.getItemRequest().getPartNumber());
			itemDetails.setQuantity(request.getItemRequest().getQuantity());
			itemDetails.setDescription(request.getItemRequest().getDescription());
			itemDetails.setUnitOfMeasure(request.getItemRequest().getUnitOfMeasure());
			itemDetails.setUnitPrice(request.getItemRequest().getUnitPrice());
			itemDetails.setCountryOfOrigin(request.getItemRequest().getCountryOfOrigin());
			itemDetails.setCodeList(codeList);

			Set<Inspection> inspectionList = request.getInspectionList().stream().map(inspectionRequest -> {
				InspectionType inspectionType = inspectionTypeRepository
						.findByInspectionType(inspectionRequest.getInspectionType())
						.orElse(InspectionType.builder().inspectionType(inspectionRequest.getInspectionType()).build());
				return Inspection.builder().inspectionType(inspectionType)
						.inspectionValue(inspectionRequest.getInspectionValue()).shipmentItem(null).build();
			}).collect(Collectors.toSet());

			shipmentItem.setAmount(request.getItemRequest().getAmount());
			shipmentItem.setItemDetails(itemDetails);
			shipmentItem.setInspectionList(inspectionList);

			invoice.setCustomerName(request.getCustomerName());
			invoice.setExportReference(request.getExportReference());
			invoice.setInvoiceDate(request.getInvoiceDate());
			invoice.setShipmentDate(request.getShipmentDate());
			invoice.setRemarks(request.getRemarks());
			invoice.setExporterDetails(exporterDetails);
			invoice.setImporterDetails(importerDetails);
			invoice.setShipmentDetails(shipmentDetail);
			invoice.setShipmentItemList(shipmentItemList);
			shipmentItemRepository.save(shipmentItem);
			invoiceRepository.save(invoice);
		}
		return ZenxlResponseBody.builder().isError(false).message(INVOICE_UPDATED_SUCCESSFULLY).build();
	}

	/**
	 * Update the remark associated with the export invoice.
	 * 
	 * @param invoiceNumber
	 * @param remark
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody updateInvoiceRemark(Long invoiceNumber, String remark) {

		ExportInvoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new InvoiceException(INVOICE_NOT_FOUND));
		invoice.setRemarks(remark);
		invoiceRepository.save(invoice);

		return ZenxlResponseBody.builder().isError(false).message(INVOICE_UPDATED_SUCCESSFULLY).build();
	}

	/**
	 * add shipment item
	 * 
	 * @param invoiceNumber
	 * @param request
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody addShipmentItem(Long invoiceNumber, ShipmentRequest request) {

		ExportInvoice exportInvoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new InvoiceException(INVOICE_NOT_FOUND));

		if (Boolean.TRUE.equals(shipmentItemRepository.existsByInternalOrderNumber(request.getInternalOrderNumber())))
			throw new ShipmentItemException(SHIPMENT_ITEM_ALREADY_EXISTS);

		if (Boolean.TRUE.equals(itemRepository.existsBySerialNumber(request.getItemRequest().getSerialNumber())))
			throw new ItemException(ITEM_ALREADY_EXISTS);

		ShipmentItem shipmentItem = ShipmentItem.builder().internalOrderNumber(request.getInternalOrderNumber())
				.build();

		ItemDetails itemDetails = ItemDetails.builder().serialNumber(request.getItemRequest().getSerialNumber())
				.build();
		Set<Code> codeList = request.getItemRequest().getCodeRequestList().stream().map(codeRequest -> {
			CodeType codeType = codeTypeRepository.findByCodeType(codeRequest.getCodeType())
					.orElse(CodeType.builder().codeType(codeRequest.getCodeType()).build());
			return Code.builder().codeType(codeType).codeValue(codeRequest.getCodeValue()).item(itemDetails).build();
		}).collect(Collectors.toSet());
		itemDetails.setManufacturerName(request.getItemRequest().getManufacturerName());
		itemDetails.setPartNumber(request.getItemRequest().getPartNumber());
		itemDetails.setQuantity(request.getItemRequest().getQuantity());
		itemDetails.setDescription(request.getItemRequest().getDescription());
		itemDetails.setUnitOfMeasure(request.getItemRequest().getUnitOfMeasure());
		itemDetails.setUnitPrice(request.getItemRequest().getUnitPrice());
		itemDetails.setCountryOfOrigin(request.getItemRequest().getCountryOfOrigin());
		itemDetails.setCodeList(codeList);

		Set<Inspection> inspectionList = request.getInspectionList().stream().map(inspectionRequest -> {
			InspectionType inspectionType = inspectionTypeRepository
					.findByInspectionType(inspectionRequest.getInspectionType())
					.orElse(InspectionType.builder().inspectionType(inspectionRequest.getInspectionType()).build());
			return Inspection.builder().inspectionType(inspectionType)
					.inspectionValue(inspectionRequest.getInspectionValue()).shipmentItem(null).build();
		}).collect(Collectors.toSet());

		shipmentItem.setAmount(request.getItemRequest().getAmount());
		shipmentItem.setItemDetails(itemDetails);
		shipmentItem.setInspectionList(inspectionList);
		shipmentItem.setExportInvoice(exportInvoice);
		shipmentItemRepository.save(shipmentItem);
		return ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(SHIPMENT_ITEM_ADDED_SUCCESSFULLY).build();
	}

	/**
	 * view shipment item
	 * 
	 * @param internalOrderNumber
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody viewShipmentItem(Long internalOrderNumber) {

		ShipmentItem shipmentItem = shipmentItemRepository.findByInternalOrderNumber(internalOrderNumber)
				.orElseThrow(() -> new ShipmentItemException(SHIPMENT_ITEM_NOT_FOUND));

		Set<CodeResponse> codeList = shipmentItem.getItemDetails().getCodeList().stream()
				.map(codeRequest -> CodeResponse.builder().codeType(codeRequest.getCodeType().getCodeType())
						.codeValue(codeRequest.getCodeValue()).build())
				.collect(Collectors.toSet());

		Set<ViewCertificateList> certificateList = shipmentItem.getItemDetails().getCertificateList().stream()
				.map(certificateRequest -> ViewCertificateList.builder()
						.certificateNumber(certificateRequest.getCertificateNumber())
						.expiryDate(certificateRequest.getExpiryDate())
						.certificateType(certificateRequest.getCertificateType().getCertificateType()).build())
				.collect(Collectors.toSet());

		ViewShipmentItem viewShipmentItem = ViewShipmentItem.builder()
				.partNumber(shipmentItem.getItemDetails().getPartNumber())
				.internalOrderNumber(shipmentItem.getInternalOrderNumber())
				.serialNumber(shipmentItem.getItemDetails().getSerialNumber())
				.manufacturerName(shipmentItem.getItemDetails().getManufacturerName())
				.description(shipmentItem.getItemDetails().getDescription())
				.quantity(shipmentItem.getItemDetails().getQuantity())
				.countryOfOrigin(shipmentItem.getItemDetails().getCountryOfOrigin())
				.unitOfMeasure(shipmentItem.getItemDetails().getUnitOfMeasure())
				.unitPrice(shipmentItem.getItemDetails().getUnitPrice()).amount(shipmentItem.getAmount())
				.codeList(codeList).certificateList(certificateList).build();

		return ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(SHIPMENT_ITEM_FETCHED_SUCCESSFULLY)
				.data(viewShipmentItem).build();
	}

	/**
	 * view shipment items list
	 * 
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody viewAllShipmentItems() {

		List<ShipmentItem> shipmentItems = shipmentItemRepository.findAll();
		if (shipmentItems.isEmpty())
			throw new ShipmentItemException(SHIPMENT_ITEM_NOT_FOUND);

		List<ViewShipmentItem> collect = shipmentItems.stream().map(shipmentItem -> {

			Set<CodeResponse> codeList = shipmentItem.getItemDetails().getCodeList().stream()
					.map(codeRequest -> CodeResponse.builder().codeType(codeRequest.getCodeType().getCodeType())
							.codeValue(codeRequest.getCodeValue()).build())
					.collect(Collectors.toSet());

			Set<ViewCertificateList> certificateList = shipmentItem.getItemDetails().getCertificateList().stream()
					.map(certificateRequest -> ViewCertificateList.builder()
							.certificateNumber(certificateRequest.getCertificateNumber())
							.expiryDate(certificateRequest.getExpiryDate())
							.certificateType(certificateRequest.getCertificateType().getCertificateType()).build())
					.collect(Collectors.toSet());

			return ViewShipmentItem.builder().partNumber(shipmentItem.getItemDetails().getPartNumber())
					.internalOrderNumber(shipmentItem.getInternalOrderNumber())
					.serialNumber(shipmentItem.getItemDetails().getSerialNumber())
					.manufacturerName(shipmentItem.getItemDetails().getManufacturerName())
					.description(shipmentItem.getItemDetails().getDescription())
					.quantity(shipmentItem.getItemDetails().getQuantity())
					.countryOfOrigin(shipmentItem.getItemDetails().getCountryOfOrigin())
					.unitOfMeasure(shipmentItem.getItemDetails().getUnitOfMeasure())
					.unitPrice(shipmentItem.getItemDetails().getUnitPrice()).amount(shipmentItem.getAmount())
					.codeList(codeList).certificateList(certificateList).build();

		}).collect(Collectors.toList());

		return ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ALL_SHIPMENT_ITEMS_FETCHED_SUCCESSFULLY)
				.data(collect).build();
	}

	/**
	 * delete shipment item
	 * 
	 * @param internalOrderNumber
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody deleteShipmentItem(Long internalOrderNumber) {

		ShipmentItem shipmentItem = shipmentItemRepository.findByInternalOrderNumber(internalOrderNumber)
				.orElseThrow(() -> new ShipmentItemException(SHIPMENT_ITEM_NOT_FOUND));

		shipmentItemRepository.delete(shipmentItem);

		return ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(SHIPMENT_ITEM_DELETED_SUCCESSFULLY).build();
	}

	/**
	 * update shipment item
	 * 
	 * @param internalOrderNumber
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody updateShipmentItem(Long internalOrderNumber, ShipmentUpdateRequest request) {

		ShipmentItem shipmentItem = shipmentItemRepository.findByInternalOrderNumber(internalOrderNumber)
				.orElseThrow(() -> new ShipmentItemException(SHIPMENT_ITEM_NOT_FOUND));

		if (!shipmentItem.getItemDetails().getSerialNumber().equals(request.getItemRequest().getSerialNumber())) {
			if (Boolean.TRUE.equals(itemRepository.existsBySerialNumber(request.getItemRequest().getSerialNumber())))
				throw new ItemException(REQUESTED_SERIAL_NUMBER_IS_ALREADY_EXISTING);
		}

		shipmentItem.getItemDetails().setSerialNumber(request.getItemRequest().getSerialNumber());
		shipmentItem.getItemDetails().setManufacturerName(request.getItemRequest().getManufacturerName());
		shipmentItem.getItemDetails().setPartNumber(request.getItemRequest().getPartNumber());
		shipmentItem.getItemDetails().setQuantity(request.getItemRequest().getQuantity());
		shipmentItem.getItemDetails().setDescription(request.getItemRequest().getDescription());
		shipmentItem.getItemDetails().setUnitOfMeasure(request.getItemRequest().getUnitOfMeasure());
		shipmentItem.getItemDetails().setUnitPrice(request.getItemRequest().getUnitPrice());
		shipmentItem.getItemDetails().setCountryOfOrigin(request.getItemRequest().getCountryOfOrigin());

		for (CodeRequest requestCodeType : request.getItemRequest().getCodeRequestList()) {
			for (Code code : shipmentItem.getItemDetails().getCodeList()) {
				if (code.getCodeType().getCodeType().equals(requestCodeType.getCodeType())) {
					code.setCodeValue(requestCodeType.getCodeValue());
				}
			}
		}

		for (InspectionRequest requestInspectionType : request.getInspectionList()) {
			for (Inspection inspection : shipmentItem.getInspectionList()) {
				if (inspection.getInspectionType().getInspectionType()
						.equals(requestInspectionType.getInspectionType())) {
					inspection.setInspectionValue(requestInspectionType.getInspectionValue());
				}
			}
		}

		shipmentItem.setAmount(request.getItemRequest().getAmount());
		shipmentItemRepository.save(shipmentItem);

		return ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(SHIPMENT_ITEM_UPDATED_SUCCESSFULLY).build();
	}

	/**
	 * Add Bill Of Entry
	 * 
	 * @param invoiceNumber
	 * @param request
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody addBillOfEntry(Long invoiceNumber, BillOfEntryRequest request) {

		ExportInvoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new InvoiceException(INVOICE_NOT_FOUND));

		BillOfEntry billOfEntry = BillOfEntry.builder().invoiceNumber(invoiceNumber)
				.invoiceDate(invoice.getInvoiceDate()).invoiceItems(invoice.getShipmentItemList().size())
				.invoiceAmount(invoice.getShipmentItemList().stream().mapToDouble(item -> item.getAmount()).sum())
				.currency(invoice.getShipmentDetails().getCurrency()).exchange(BigDecimal.ZERO)
				.portCode(request.getPortCode()).boeNumber(request.getBoeNumber()).boeDate(request.getBoeDate())
				.grossWeight(request.getGrossWeight()).cHAgent(request.getCHAgent())
				.duty(DutySummary.builder().bcd(request.getBcd()).acd(request.getAcd()).sws(request.getSws())
						.nccd(request.getNccd()).add(request.getAdd()).cvd(request.getCvd()).igst(request.getIgst())
						.cess(request.getCess()).assessedvalue(request.getAssessedvalue())
						.totalDuty(request.getTotalDuty()).iNT(request.getINT()).pnlty(request.getPnlty())
						.fine(request.getFine()).totalDutyAmount(request.getTotalDutyAmount()).build())
				.build();

		billOfEntryRepository.save(billOfEntry);

		return ZenxlResponseBody.builder().isError(false).message(BILL_OF_ENTRY_ADDED_SUCCESSFULLY).build();
	}

	/**
	 * View Bill Of Entry
	 * 
	 * @param invoiceNumber
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody viewBillOfEntry(Long invoiceNumber) {

		BillOfEntry billOfEntry = billOfEntryRepository.findByInvoiceNumber(invoiceNumber)
				.orElseThrow(() -> new BillOfEntryException(BILL_OF_ENTRY_NOT_FOUND));

		return ZenxlResponseBody.builder().isError(false).message(BILL_OF_ENTRY_FETCHED_SUCCESSFULLY).data(billOfEntry)
				.build();
	}

	public ZenxlResponseBody viewAllBillOfEntry() {

		List<BillOfEntry> findAllBillOfEntries = billOfEntryRepository.findAll();

		return ZenxlResponseBody.builder().isError(false).message(ALL_BILL_OF_ENTRIES_ARE_FETCHED_SUCCESSFULLY)
				.data(findAllBillOfEntries).build();
	}

	/**
	 * Update Bill Of Entry
	 * 
	 * @param billOfEntryId
	 * @param request
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody updateBillOfEntry(Integer billOfEntryId, DutySummaryRequest request) {

		BillOfEntry billOfEntry = billOfEntryRepository.findByBillOfEntryId(billOfEntryId)
				.orElseThrow(() -> new BillOfEntryException(BILL_OF_ENTRY_NOT_FOUND));

		billOfEntry.getDuty().setBcd(request.getBcd());
		billOfEntry.getDuty().setAcd(request.getAcd());
		billOfEntry.getDuty().setSws(request.getSws());
		billOfEntry.getDuty().setNccd(request.getNccd());
		billOfEntry.getDuty().setAdd(request.getAdd());
		billOfEntry.getDuty().setCvd(request.getCvd());
		billOfEntry.getDuty().setIgst(request.getIgst());
		billOfEntry.getDuty().setCess(request.getCess());
		billOfEntry.getDuty().setAssessedvalue(request.getAssessedvalue());
		billOfEntry.getDuty().setTotalDuty(request.getTotalDuty());
		billOfEntry.getDuty().setINT(request.getINT());
		billOfEntry.getDuty().setPnlty(request.getPnlty());
		billOfEntry.getDuty().setFine(request.getFine());
		billOfEntry.getDuty().setTotalDutyAmount(request.getTotalDutyAmount());

		billOfEntryRepository.save(billOfEntry);

		return ZenxlResponseBody.builder().isError(false).message(BILL_OF_ENTRY_UPDATED_SUCCESSFULLY).build();
	}

	/**
	 * Delete Bill Of Entry
	 * 
	 * @param billOfEntryId
	 * @return {@link ZenxlResponseBody}
	 */
	public ZenxlResponseBody deleteBillOfEntry(Integer billOfEntryId) {

		BillOfEntry billOfEntry = billOfEntryRepository.findByBillOfEntryId(billOfEntryId)
				.orElseThrow(() -> new BillOfEntryException(BILL_OF_ENTRY_NOT_FOUND));
		billOfEntryRepository.delete(billOfEntry);

		return ZenxlResponseBody.builder().isError(false).message(BILL_OF_ENTRY_DELETED_SUCCESSFULLY).build();
	}

}
