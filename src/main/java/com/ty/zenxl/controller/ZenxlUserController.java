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
import com.ty.zenxl.request.SignUpRequest;
import com.ty.zenxl.request.UpdateUserRequest;
import com.ty.zenxl.response.UserResponse;
import com.ty.zenxl.service.ZenxlUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

/**
 * Defines all the apis to perform CRUD operations related to users.
 * 
 * Permitted to be accessed by Admin.
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/zenxl/user")
@SecurityRequirement(name = "zenxl-api")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ZenxlUserController {
	
	private final ZenxlUserService zenxlUserService;
	
	@PostMapping("/add-user")
	public ResponseEntity<ZenxlResponseBody> addUser(@Valid @RequestBody SignUpRequest request) {
		
		UserResponse addUser = zenxlUserService.addUser(request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(ADDED_SUCCESSFULLY).data(addUser).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(zenxlResponseBody);
	}
	
	@GetMapping("/find-all-users")
	public ResponseEntity<List<UserResponse>> findAllUsers(){
		return ResponseEntity.ok(zenxlUserService.findAllUsers());
	}
	
	@PutMapping("/update-user")
	public ResponseEntity<ZenxlResponseBody> updateUser(@RequestHeader int userId,@Valid @RequestBody UpdateUserRequest request){
		
		String updateUserMessage = zenxlUserService.updateUser(userId,request);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(updateUserMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<ZenxlResponseBody> deleteUser(@RequestHeader int userId){
		
		String deleteUserMessage = zenxlUserService.deleteUser(userId);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(deleteUserMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}
	
	@PutMapping("/change-user-status")
	public ResponseEntity<ZenxlResponseBody> setUserStatus(@RequestHeader int userId, @RequestHeader boolean status) {
		
		String setUserStatusMessage = zenxlUserService.setUserStatus(userId,status);
		ZenxlResponseBody zenxlResponseBody = ZenxlResponseBody.builder().isError(IS_ERROR_FALSE).message(setUserStatusMessage).build();
		return ResponseEntity.status(HttpStatus.OK).body(zenxlResponseBody);
	}

}
