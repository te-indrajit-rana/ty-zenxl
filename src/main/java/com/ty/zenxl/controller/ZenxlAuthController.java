package com.ty.zenxl.controller;

import static com.ty.zenxl.pojos.ZenxlConstantData.IS_ERROR_FALSE;
import static com.ty.zenxl.pojos.ZenxlConstantData.LOGIN_SUCCESSFUL;
import static com.ty.zenxl.pojos.ZenxlConstantData.SIGN_UP_SUCCESSFUL;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.zenxl.pojos.JwtToken;
import com.ty.zenxl.pojos.ZenxlResponseBody;
import com.ty.zenxl.request.ChangePasswordRequest;
import com.ty.zenxl.request.LoginRequest;
import com.ty.zenxl.request.SignUpRequest;
import com.ty.zenxl.response.UserResponse;
import com.ty.zenxl.service.ZenxlAuthService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

/**
 * Defines all the apis related to authentication.
 * 
 * Permitted to be accessed by all.
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/zenxl/auth")
@SecurityRequirement(name = "zenxl-api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ZenxlAuthController {

	private final ZenxlAuthService zenxlAuthService;

	@PostMapping("/login")
	public ResponseEntity<ZenxlResponseBody> authenticateUser(@Valid @RequestBody LoginRequest request)
			throws AuthenticationException {
		
		String authenticationToken = zenxlAuthService.authenticateUser(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(LOGIN_SUCCESSFUL)
				.data(JwtToken.builder().tokenType("Bearer").token(authenticationToken).build()).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@PostMapping("/admin-registration")
	public ResponseEntity<ZenxlResponseBody> adminRegistration(@Valid @RequestBody SignUpRequest request) {
		
		UserResponse adminRegistration = zenxlAuthService.adminRegistration(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(SIGN_UP_SUCCESSFUL).data(adminRegistration).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}

	@GetMapping("/forgot-password")
	public ResponseEntity<ZenxlResponseBody> forgotPassword(@RequestHeader String email,HttpServletRequest request) {
		
		String forgotPasswordMessage = zenxlAuthService.forgotPassword(email,request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(forgotPasswordMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

	@PutMapping("/change-password")
	public ResponseEntity<ZenxlResponseBody> changePassword(@RequestHeader String email,
			@Valid @RequestBody ChangePasswordRequest request) {
		
		String changePasswordMessage = zenxlAuthService.changePassword(email, request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE)
				.message(changePasswordMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

}
