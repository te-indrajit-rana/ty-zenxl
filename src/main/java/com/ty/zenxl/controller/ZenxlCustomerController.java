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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.zenxl.pojos.ZenxlResponseBody;
import com.ty.zenxl.request.CustomerRequest;
import com.ty.zenxl.request.UpdateCustomerRequest;
import com.ty.zenxl.response.CustomerResponse;
import com.ty.zenxl.response.ViewCustomerResponse;
import com.ty.zenxl.service.ZenxlCustomerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

/**
 * Defines all the apis to perfom CRUD operations related to Customers.
 * 
 * Permitted to be accessed by Admin.
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/zenxl/customer")
@SecurityRequirement(name = "zenxl-api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ZenxlCustomerController {

	private final ZenxlCustomerService zenxlCustomerService;

	@PostMapping("/add-customer")
	public ResponseEntity<ZenxlResponseBody> addCustomer(@Valid @RequestBody CustomerRequest request) {

		CustomerResponse addCustomer = zenxlCustomerService.addCustomer(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(ADDED_SUCCESSFULLY).data(addCustomer).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/view-customer/{customerId}")
	public ResponseEntity<ViewCustomerResponse> viewCustomer(@PathVariable int customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(zenxlCustomerService.viewCustomer(customerId));
	}

	@GetMapping("/find-all-customers")
	public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
		return ResponseEntity.status(HttpStatus.OK).body(zenxlCustomerService.findAllCustomers());
	}

	@PutMapping("/update-customer/{customerId}")
	public ResponseEntity<ZenxlResponseBody> updateCustomer(@PathVariable int customerId,
			@Valid @RequestBody UpdateCustomerRequest request) {
		
		String updateCustomerMessage = zenxlCustomerService.updateCustomer(customerId, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateCustomerMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@DeleteMapping("/delete-customer/{customerId}")
	public ResponseEntity<ZenxlResponseBody> deleteCustomer(@PathVariable int customerId) {
		
		String deleteCustomerMessage = zenxlCustomerService.deleteCustomer(customerId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteCustomerMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

}
