package com.ty.zenxl.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.zenxl.request.ItemRequest;
import com.ty.zenxl.request.ItemUpdateRequest;
import com.ty.zenxl.response.FileResponse;
import com.ty.zenxl.response.ItemDashboardResponse;
import com.ty.zenxl.response.ItemDetailsResponse;
import com.ty.zenxl.response.ItemResponse;
import com.ty.zenxl.response.ViewCertificate;
import com.ty.zenxl.service.ZenxlItemService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/zenxl/item")
@SecurityRequirement(name = "zenxl-api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ZenxlItemController {

	private final ZenxlItemService itemService;

	@PostMapping(value = "/upload-certificate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Validated
	public ResponseEntity<FileResponse> uploadCertificate(@NotNull @RequestPart MultipartFile file,
			@NotNull @RequestHeader String data) throws IOException {
		return ResponseEntity.ok(itemService.uploadCertificate(file, data));
	}

	@GetMapping(value = "/view-certificate", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Validated
	public ResponseEntity<Resource> viewCertificate(@NotNull @RequestHeader String certificateNumber)
			throws MalformedURLException {
		ViewCertificate certificate = itemService.viewCertificate(certificateNumber);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(certificate.getCertificateFormat()))
				.body(certificate.getResource());
	}

	@PutMapping(value = "/update-certificate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Validated
	public ResponseEntity<FileResponse> updateCertificate(@NotNull @RequestPart MultipartFile file,
			@NotNull @RequestHeader String data) throws IOException {
		return ResponseEntity.ok(itemService.updateCertificate(file, data));
	}

	@PostMapping("/add-item")
	@Validated
	public ResponseEntity<ItemResponse> addItem(@NotNull @Valid @RequestBody ItemRequest request) {
		return ResponseEntity.ok(itemService.addItem(request));
	}

	@GetMapping("/view-item")
	@Validated
	public ResponseEntity<ItemDetailsResponse> viewItem(@RequestHeader @NotNull Long serialNumber) {
		return ResponseEntity.ok(itemService.viewItem(serialNumber));
	}

	@GetMapping("/view-all-items")
	public ResponseEntity<ItemDashboardResponse> viewAllItems() {
		return ResponseEntity.ok(itemService.viewAllItems());
	}

	@DeleteMapping("/delete-item")
	@Validated
	public ResponseEntity<ItemResponse> deleteItem(@NotNull @RequestHeader Long serialNumber) {
		return ResponseEntity.ok(itemService.deleteItem(serialNumber));
	}

	@PutMapping("/update-item")
	@Validated
	public ResponseEntity<ItemResponse> updateItem(@NotNull @Valid @RequestBody ItemUpdateRequest request,
			@NotNull @RequestHeader Long serialNumber) {
		return ResponseEntity.ok(itemService.updateItem(request, serialNumber));
	}

}
