package com.ty.zenxl.controller;

import static com.ty.zenxl.pojos.ZenxlConstantData.ADDED_SUCCESSFULLY;
import static com.ty.zenxl.pojos.ZenxlConstantData.IS_ERROR_FALSE;

import java.util.List;

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

import com.ty.zenxl.pojos.ZenxlResponseBody;
import com.ty.zenxl.request.CertificateRequest;
import com.ty.zenxl.request.CodeRequest;
import com.ty.zenxl.request.HsCodeRequest;
import com.ty.zenxl.request.IncotermRequest;
import com.ty.zenxl.request.InspectionRequest;
import com.ty.zenxl.request.UpdateCertificateRequest;
import com.ty.zenxl.request.UpdateCodeRequest;
import com.ty.zenxl.request.UpdateHsCodeRequest;
import com.ty.zenxl.request.UpdateIncotermRequest;
import com.ty.zenxl.request.UpdateInspectionRequest;
import com.ty.zenxl.response.CertificateResponse;
import com.ty.zenxl.response.CodeResponse;
import com.ty.zenxl.response.HsCodeResponse;
import com.ty.zenxl.response.IncotermResponse;
import com.ty.zenxl.response.InspectionResponse;
import com.ty.zenxl.service.ZenxlUtilityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

/**
 * Defines all the apis to perform CRUD operations related to all utilities.
 * 
 * Permitted to be accessed by Admin.
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/zenxl/utility")
@SecurityRequirement(name = "zenxl-api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ZenxlUtilityController {

	private final ZenxlUtilityService zenxlUtilityService;

	// certificate crud apis

	@PostMapping("/add-certificate")
	public ResponseEntity<ZenxlResponseBody> addCertificate(@Valid @RequestBody CertificateRequest request) {

		CertificateResponse addCertificate = zenxlUtilityService.addCertificate(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(ADDED_SUCCESSFULLY).data(addCertificate).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-certificates")
	public ResponseEntity<List<CertificateResponse>> findAllCertificates() {
		return ResponseEntity.ok(zenxlUtilityService.findAllCertificates());
	}

	@PutMapping("/update-certificate")
	public ResponseEntity<ZenxlResponseBody> updateCertificate(@RequestHeader int certificateId,
			@Valid @RequestBody UpdateCertificateRequest request) {
		
		String updateCertificateMessage = zenxlUtilityService.updateCertificate(certificateId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateCertificateMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-certificate")
	public ResponseEntity<ZenxlResponseBody> deleteCertificate(@RequestHeader int certificateId) {
		
		String deleteCertificateMessage = zenxlUtilityService.deleteCertificate(certificateId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteCertificateMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// code crud apis

	@PostMapping("/add-code")
	public ResponseEntity<ZenxlResponseBody> addCode(@Valid @RequestBody CodeRequest request) {
		
		CodeResponse addCode = zenxlUtilityService.addCode(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(ADDED_SUCCESSFULLY).data(addCode).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-codes")
	public ResponseEntity<List<CodeResponse>> findAllCodes() {
		return ResponseEntity.ok(zenxlUtilityService.findAllCodes());
	}

	@PutMapping("/update-code")
	public ResponseEntity<ZenxlResponseBody> updateCode(@RequestHeader int codeId,
			@Valid @RequestBody UpdateCodeRequest request) {
		
		String updateCodeMessage = zenxlUtilityService.updateCode(codeId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateCodeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-code")
	public ResponseEntity<ZenxlResponseBody> deleteCode(@RequestHeader int codeId) {
		
		String deleteCodeMessage = zenxlUtilityService.deleteCode(codeId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteCodeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// incoterm crud apis

	@PostMapping("/add-incoterm")
	public ResponseEntity<ZenxlResponseBody> addIncoterm(@Valid @RequestBody IncotermRequest request) {
		
		IncotermResponse addIncoterm = zenxlUtilityService.addIncoterm(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ADDED_SUCCESSFULLY).data(addIncoterm).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-incoterms")
	public ResponseEntity<List<IncotermResponse>> findAllIncoterms() {
		return ResponseEntity.ok(zenxlUtilityService.findAllIncoterms());
	}

	@PutMapping("/update-incoterm")
	public ResponseEntity<ZenxlResponseBody> updateIncoterm(@RequestHeader int incotermId,
			@Valid @RequestBody UpdateIncotermRequest request) {
		
		String updateIncotermMessage = zenxlUtilityService.updateIncoterm(incotermId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateIncotermMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-incoterm")
	public ResponseEntity<ZenxlResponseBody> deleteIncoterm(@RequestHeader int incotermId) {
		
		String deleteIncotermMessage = zenxlUtilityService.deleteIncoterm(incotermId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteIncotermMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// inspection crud apis

	@PostMapping("/add-inspection")
	public ResponseEntity<ZenxlResponseBody> addInspection(@Valid @RequestBody InspectionRequest request) {
		
		InspectionResponse addInspection = zenxlUtilityService.addInspection(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ADDED_SUCCESSFULLY).data(addInspection).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-inspections")
	public ResponseEntity<List<InspectionResponse>> findAllInspections() {
		return ResponseEntity.ok(zenxlUtilityService.findAllInspections());
	}

	@PutMapping("/update-inspection")
	public ResponseEntity<ZenxlResponseBody> updateInspection(@RequestHeader int inspectionId,
			@Valid @RequestBody UpdateInspectionRequest request) {
		
		String updateInspectionMessage = zenxlUtilityService.updateInspection(inspectionId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateInspectionMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-inspection")
	public ResponseEntity<ZenxlResponseBody> deleteInspection(@RequestHeader int inspectionId) {
		
		String deleteInspectionMessage = zenxlUtilityService.deleteInspection(inspectionId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteInspectionMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// hscode crud apis

		@PostMapping("/add-hscode")
		public ResponseEntity<ZenxlResponseBody> addHsCode(@Valid @RequestBody HsCodeRequest request) {
			
			HsCodeResponse addHsCode = zenxlUtilityService.addHsCode(request);
			ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ADDED_SUCCESSFULLY).data(addHsCode).build();
			return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
		}

		@GetMapping("/find-all-hscodes")
		public ResponseEntity<List<HsCodeResponse>> findAllHsCodes() {
			return ResponseEntity.ok(zenxlUtilityService.findAllHsCodes());
		}

		@PutMapping("/update-hscode")
		public ResponseEntity<ZenxlResponseBody> updateHscode(@RequestHeader int hsCodeId,
				@Valid @RequestBody UpdateHsCodeRequest request) {
			
			String updateHsCodeMessage = zenxlUtilityService.updateHsCode(hsCodeId, request);
			ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateHsCodeMessage).build();
			return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
		}

		@DeleteMapping("/delete-hscode")
		public ResponseEntity<ZenxlResponseBody> deleteHsCode(@RequestHeader int hsCodeId) {
			
			String deleteHsCodeMessage = zenxlUtilityService.deleteHsCode(hsCodeId);
			ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteHsCodeMessage).build();
			return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
		}

}
