package com.ty.zenxl.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.zenxl.request.BillOfEntryRequest;
import com.ty.zenxl.request.DutySummaryRequest;
import com.ty.zenxl.request.ImportInvoiceRequest;
import com.ty.zenxl.request.ImportInvoiceUpdate;
import com.ty.zenxl.request.ShipmentRequest;
import com.ty.zenxl.request.ShipmentUpdateRequest;
import com.ty.zenxl.response.InvoiceListResponse;
import com.ty.zenxl.response.InvoiceResponse;
import com.ty.zenxl.response.ZenxlResponseBody;
import com.ty.zenxl.service.ZenxlImportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/zenxl/import")
@RequiredArgsConstructor
@SecurityRequirement(name = "zenxl-api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ZenxlImportController {

	private final ZenxlImportService importService;

	@PostMapping("/add-invoice")
	public ResponseEntity<InvoiceResponse> addInvoice(@Valid @RequestBody ImportInvoiceRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(importService.addInvoice(request));
	}

	@GetMapping("/view-invoice")
	public ResponseEntity<ZenxlResponseBody> viewInvoice(@RequestHeader Long invoiceNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(importService.viewInvoice(invoiceNumber));
	}

	@GetMapping("/view-invoice-list")
	public ResponseEntity<InvoiceListResponse> viewAllInvoice() {
		return ResponseEntity.status(HttpStatus.OK).body(importService.viewAllInvoices());
	}

	@PutMapping("/update-invoice")
	public ResponseEntity<ZenxlResponseBody> updateInvoice(@RequestHeader Long invoiceNumber,
			@Valid @RequestBody ImportInvoiceUpdate request) {
		return ResponseEntity.status(HttpStatus.OK).body(importService.updateInvoice(invoiceNumber, request));
	}

	@DeleteMapping("/delete-invoice")
	public ResponseEntity<ZenxlResponseBody> deleteInvoice(@RequestHeader Long invoiceNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(importService.deleteInvoice(invoiceNumber));
	}

	@PutMapping("/update-invoice-remark")
	public ResponseEntity<ZenxlResponseBody> updateInvoiceRemark(@RequestHeader Long invoiceNumber,
			@RequestHeader String remark) {
		return ResponseEntity.status(HttpStatus.OK).body(importService.updateInvoiceRemark(invoiceNumber, remark));
	}

	@PostMapping("/add-shipment-item")
	public ResponseEntity<ZenxlResponseBody> addShipmentItem(@RequestHeader Long invoiceNumber,
			@Valid @RequestBody ShipmentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(importService.addShipmentItem(invoiceNumber, request));
	}

	@GetMapping("/view-shipment-item")
	public ResponseEntity<ZenxlResponseBody> viewShipmentItem(@RequestHeader Long internalOrderNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(importService.viewShipmentItem(internalOrderNumber));
	}

	@GetMapping("/view-shipment-item-list")
	public ResponseEntity<ZenxlResponseBody> viewAllShipmentItems() {
		return ResponseEntity.status(HttpStatus.OK).body(importService.viewAllShipmentItems());
	}

	@PutMapping("/update-shipment-item")
	public ResponseEntity<ZenxlResponseBody> updateShipmentItem(@RequestHeader Long internalOrderNumber,
			@Valid @RequestBody ShipmentUpdateRequest request) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(importService.updateShipmentItem(internalOrderNumber, request));
	}

	@DeleteMapping("/delete-shipment-item")
	public ResponseEntity<ZenxlResponseBody> deleteShipmentItem(@RequestHeader Long internalOrderNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(importService.deleteShipmentItem(internalOrderNumber));
	}
	
	//BOE CRUD Operations
	
		@PostMapping("/add-boe")
		public ResponseEntity<ZenxlResponseBody> addBillOfEntry(@RequestHeader Long invoiceNumber, @Valid @RequestBody BillOfEntryRequest request) {
			return ResponseEntity.status(HttpStatus.CREATED).body(importService.addBillOfEntry(invoiceNumber,request));
		}
		
		@GetMapping("/view-boe")
		public ResponseEntity<ZenxlResponseBody> viewBillOfEntry(@RequestHeader Long invoiceNumber) {
			return ResponseEntity.status(HttpStatus.OK).body(importService.viewBillOfEntry(invoiceNumber));
		}
		
		@GetMapping("/view-all-boe")
		public ResponseEntity<ZenxlResponseBody> viewAllBillOfEntry() {
			return ResponseEntity.status(HttpStatus.OK).body(importService.viewAllBillOfEntry());
		}

		@PutMapping("/update-boe")
		public ResponseEntity<ZenxlResponseBody> updateBillOfEntry(@RequestHeader Integer billOfEntryId,
				@Valid @RequestBody DutySummaryRequest request) {
			return ResponseEntity.status(HttpStatus.OK).body(importService.updateBillOfEntry(billOfEntryId, request));
		}

		@DeleteMapping("/delete-boe")
		public ResponseEntity<ZenxlResponseBody> deleteBillOfEntry(@RequestHeader Integer billOfEntryId) {
			return ResponseEntity.status(HttpStatus.OK).body(importService.deleteBillOfEntry(billOfEntryId));
		}

}
