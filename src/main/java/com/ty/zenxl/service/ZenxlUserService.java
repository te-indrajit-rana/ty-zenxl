package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.Role;
import com.ty.zenxl.entity.User;
import com.ty.zenxl.exception.ChangeStatusException;
import com.ty.zenxl.exception.UpdateException;
import com.ty.zenxl.exception.UserNotFoundException;
import com.ty.zenxl.exception.UserPersistenceException;
import com.ty.zenxl.repository.RoleRepository;
import com.ty.zenxl.repository.UserRepository;
import com.ty.zenxl.request.SignUpRequest;
import com.ty.zenxl.request.UpdateUserRequest;
import com.ty.zenxl.response.UserResponse;

import lombok.RequiredArgsConstructor;

/**
 * Defines the mechanisms implemented for CRUD opeartions for a {@code User}.
 *
 * @author Indrajit
 * @version 1.0
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ZenxlUserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private PasswordEncoder encoder = new BCryptPasswordEncoder();

	public UserResponse addUser(SignUpRequest request) {
		if (Boolean.TRUE.equals(userRepository.existsByUsername(request.getUsername()))) {
			throw new UserPersistenceException(USERNAME_ALREADY_EXISTS);
		}
		if (Boolean.TRUE.equals(userRepository.existsByEmail(request.getEmail()))) {
			throw new UserPersistenceException(EMAIL_ALREADY_EXISTS);
		}

		String requestedRole = request.getRole();
		if (!requestedRole.startsWith("ROLE_")) {
			requestedRole = "ROLE_" + requestedRole.toUpperCase();
		}

		Role role = roleRepository.findByRoleName(requestedRole).orElse(Role.builder().roleName(requestedRole).build());

		User user = User.builder().username(request.getUsername()).email(request.getEmail())
				.dateOfBirth(request.getDateOfBirth()).gender(request.getGender())
				.password(encoder.encode(request.getPassword())).active(Boolean.TRUE).role(role).build();

		User savedUser = userRepository.save(user);

		if (savedUser.getUsername() != null) {

			return UserResponse.builder().userId(savedUser.getUserId()).username(savedUser.getUsername()).build();
		}
		throw new UserPersistenceException(SIGN_UP_UNSUCCESSFUL);
	}

	public List<UserResponse> findAllUsers() {
		List<User> findAllUsers = userRepository.findAll();
		return findAllUsers.stream()
				.map(user -> UserResponse.builder().userId(user.getUserId()).username(user.getUsername())
						.email(user.getEmail()).role(user.getRole().getRoleName()).dateOfBirth(user.getDateOfBirth()).gender(user.getGender()).status(user.getActive()).build())
				.collect(Collectors.toList());
	}

	public String updateUser(int userId, UpdateUserRequest request) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with user id " + userId));

		if (!request.getUsername().equals(user.getUsername())) {
			if (Boolean.TRUE.equals(userRepository.existsByUsername(request.getUsername()))) {
				throw new UserPersistenceException(USERNAME_ALREADY_EXISTS);
			}
			if (!request.getEmail().equals(user.getEmail())) {
				if (Boolean.TRUE.equals(userRepository.existsByEmail(request.getEmail()))) {
					throw new UserPersistenceException(EMAIL_ALREADY_EXISTS);
				}
			}
		}

		String requestedRole = request.getRole();
		if (!requestedRole.startsWith("ROLE_")) {
			requestedRole = "ROLE_" + requestedRole.toUpperCase();
		}
		Role role = roleRepository.findByRoleName(requestedRole)
				.orElse(Role.builder().roleName(requestedRole).build());

		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setDateOfBirth(request.getDateOfBirth());
		user.setGender(request.getGender());
		user.setRole(role);

		User savedUser = userRepository.save(user);

		if (savedUser != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteUser(int userId) {
		User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with user id " + userId));
		userRepository.deleteUser(user.getUserId());
		return DELETED_SUCCESSFULLY;
	}

	public String setUserStatus(int userId, boolean status) {
		User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with userId " + userId));
		user.setActive(status);
		User updatedUser = userRepository.save(user);
		if (updatedUser == null) {
			throw new ChangeStatusException(UNABLE_TO_CHANGE_USER_STATUS);
		}
		return USER_STATUS_CHANGED_SUCCESSFULLY;
	}

}
