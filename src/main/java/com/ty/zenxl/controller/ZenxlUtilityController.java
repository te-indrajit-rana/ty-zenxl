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

import com.ty.zenxl.request.CertificateTypeRequest;
import com.ty.zenxl.request.CodeTypeRequest;
import com.ty.zenxl.request.HsCodeRequest;
import com.ty.zenxl.request.IncotermTypeRequest;
import com.ty.zenxl.request.InspectionTypeRequest;
import com.ty.zenxl.request.UpdateCertificateTypeRequest;
import com.ty.zenxl.request.UpdateCodeTypeRequest;
import com.ty.zenxl.request.UpdateHsCodeRequest;
import com.ty.zenxl.request.UpdateIncotermTypeRequest;
import com.ty.zenxl.request.UpdateInspectionTypeRequest;
import com.ty.zenxl.response.CertificateTypeResponse;
import com.ty.zenxl.response.CodeTypeResponse;
import com.ty.zenxl.response.HsCodeResponse;
import com.ty.zenxl.response.IncotermTypeResponse;
import com.ty.zenxl.response.InspectionTypeResponse;
import com.ty.zenxl.response.ZenxlResponseBody;
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

	// certificate-type crud apis

	@PostMapping("/add-certificate-type")
	public ResponseEntity<ZenxlResponseBody> addCertificateType(@Valid @RequestBody CertificateTypeRequest request) {

		CertificateTypeResponse addCertificateType = zenxlUtilityService.addCertificateType(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(ADDED_SUCCESSFULLY).data(addCertificateType).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-certificate-types")
	public ResponseEntity<List<CertificateTypeResponse>> findAllCertificateTypes() {
		return ResponseEntity.ok(zenxlUtilityService.findAllCertificateTypes());
	}

	@PutMapping("/update-certificate-type")
	public ResponseEntity<ZenxlResponseBody> updateCertificateType(@RequestHeader int certificateTypeId,
			@Valid @RequestBody UpdateCertificateTypeRequest request) {
		
		String updateCertificateTypeMessage = zenxlUtilityService.updateCertificateType(certificateTypeId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateCertificateTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-certificate-type")
	public ResponseEntity<ZenxlResponseBody> deleteCertificateType(@RequestHeader int certificateTypeId) {
		
		String deleteCertificateTypeMessage = zenxlUtilityService.deleteCertificateType(certificateTypeId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteCertificateTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// code-type crud apis

	@PostMapping("/add-code-type")
	public ResponseEntity<ZenxlResponseBody> addCodeType(@Valid @RequestBody CodeTypeRequest request) {
		
		CodeTypeResponse addCodeType = zenxlUtilityService.addCodeType(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(ADDED_SUCCESSFULLY).data(addCodeType).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-code-types")
	public ResponseEntity<List<CodeTypeResponse>> findAllCodeTypes() {
		return ResponseEntity.ok(zenxlUtilityService.findAllCodeTypes());
	}

	@PutMapping("/update-code-type")
	public ResponseEntity<ZenxlResponseBody> updateCodeType(@RequestHeader int codeTypeId,
			@Valid @RequestBody UpdateCodeTypeRequest request) {
		
		String updateCodeTypeMessage = zenxlUtilityService.updateCodeType(codeTypeId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateCodeTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-code-type")
	public ResponseEntity<ZenxlResponseBody> deleteCodeType(@RequestHeader int codeTypeId) {
		
		String deleteCodeTypeMessage = zenxlUtilityService.deleteCodeType(codeTypeId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteCodeTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// incoterm-type crud apis

	@PostMapping("/add-incoterm-type")
	public ResponseEntity<ZenxlResponseBody> addIncotermType(@Valid @RequestBody IncotermTypeRequest request) {
		
		IncotermTypeResponse addIncotermType = zenxlUtilityService.addIncotermType(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ADDED_SUCCESSFULLY).data(addIncotermType).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-incoterm-types")
	public ResponseEntity<List<IncotermTypeResponse>> findAllIncotermTypes() {
		return ResponseEntity.ok(zenxlUtilityService.findAllIncotermTypes());
	}

	@PutMapping("/update-incoterm-type")
	public ResponseEntity<ZenxlResponseBody> updateIncotermType(@RequestHeader int incotermTypeId,
			@Valid @RequestBody UpdateIncotermTypeRequest request) {
		
		String updateIncotermTypeMessage = zenxlUtilityService.updateIncotermType(incotermTypeId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateIncotermTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-incoterm-type")
	public ResponseEntity<ZenxlResponseBody> deleteIncotermType(@RequestHeader int incotermTypeId) {
		
		String deleteIncotermTypeMessage = zenxlUtilityService.deleteIncotermType(incotermTypeId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteIncotermTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	// inspection-type crud apis

	@PostMapping("/add-inspection-type")
	public ResponseEntity<ZenxlResponseBody> addInspectionType(@Valid @RequestBody InspectionTypeRequest request) {
		
		InspectionTypeResponse addInspectionType = zenxlUtilityService.addInspectionType(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ADDED_SUCCESSFULLY).data(addInspectionType).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/find-all-inspection-types")
	public ResponseEntity<List<InspectionTypeResponse>> findAllInspectionTypes() {
		return ResponseEntity.ok(zenxlUtilityService.findAllInspectionTypes());
	}

	@PutMapping("/update-inspection-type")
	public ResponseEntity<ZenxlResponseBody> updateInspectionType(@RequestHeader int inspectionTypeId,
			@Valid @RequestBody UpdateInspectionTypeRequest request) {
		
		String updateInspectionTypeMessage = zenxlUtilityService.updateInspectionType(inspectionTypeId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateInspectionTypeMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-inspection-type")
	public ResponseEntity<ZenxlResponseBody> deleteInspectionType(@RequestHeader int inspectionTypeId) {
		
		String deleteInspectionTypeMessage = zenxlUtilityService.deleteInspectionType(inspectionTypeId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteInspectionTypeMessage).build();
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
