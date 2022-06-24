package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.util.Optional;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.User;
import com.ty.zenxl.pojos.ZenxlCustomUserDetails;
import com.ty.zenxl.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@code UserDetailsService} to fetch the {@code UserDetails} by loadUserByUsername
 * 
 * Throws {@code DisabledException } if it finds the account not active.
 * 
 * Throws {@code UsernameNotFoundException } if the user if not present with the mentioned username.
 * 
 * @author Indrajit
 * @version 1.0
 */


@Service
@RequiredArgsConstructor
public class ZenxlCustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> findByUsername = userRepository.findByEmail(email);

		if (findByUsername.isPresent()) {
			if (findByUsername.get().getActive()) {
				return new ZenxlCustomUserDetails(findByUsername.get());
			} else {
				throw new DisabledException(ACCOUNT_IS_CURRENTLY_INACTIVE);
			}
		} else {
			throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_THE_EMAIL + email);
		}
	}

}
