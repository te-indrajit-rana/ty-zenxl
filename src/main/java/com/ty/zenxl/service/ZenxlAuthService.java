
package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.ACCOUNT_IS_CURRENTLY_INACTIVE;
import static com.ty.zenxl.pojos.ZenxlConstantData.ACCOUNT_IS_CURRENTLY_LOCKED;
import static com.ty.zenxl.pojos.ZenxlConstantData.BOTH_PASSWORDS_SHOULD_BE_SAME;
import static com.ty.zenxl.pojos.ZenxlConstantData.ENTERED_PASSCODE_NOT_VALID;
import static com.ty.zenxl.pojos.ZenxlConstantData.INCORRECT_EMAIL_AND_PASSWORD;
import static com.ty.zenxl.pojos.ZenxlConstantData.OLD_PASSWORD_AND_NEW_PASSWORD_SHOULD_BE_DIFFERENT;
import static com.ty.zenxl.pojos.ZenxlConstantData.ONLY_REGISTERED_ADMIN_CAN_ADD_USERS;
import static com.ty.zenxl.pojos.ZenxlConstantData.PASSCODE_HAS_BEEN_SENT;
import static com.ty.zenxl.pojos.ZenxlConstantData.PASSCODE_NOT_FOUND_WITH_ENTERED_EMAIL;
import static com.ty.zenxl.pojos.ZenxlConstantData.PASSWORD_RESET_SUCCESSFUL;
import static com.ty.zenxl.pojos.ZenxlConstantData.SIGN_UP_UNSUCCESSFUL;
import static com.ty.zenxl.pojos.ZenxlConstantData.THE_FIRST_REGISTERED_USER_MUST_BE_ADMIN_ONLY;
import static com.ty.zenxl.pojos.ZenxlConstantData.USER_DOESN_T_EXIST_WITH_THE_ENTERED_EMAIL;
import static com.ty.zenxl.pojos.ZenxlConstantData.USER_NOT_FOUND_WITH_THIS_EMAIL_ADDRESS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.Passcode;
import com.ty.zenxl.entity.Role;
import com.ty.zenxl.entity.User;
import com.ty.zenxl.exception.ChangePasswordException;
import com.ty.zenxl.exception.EmailInterruptionException;
import com.ty.zenxl.exception.UserNotFoundException;
import com.ty.zenxl.exception.UserPersistenceException;
import com.ty.zenxl.pojos.JwtUtils;
import com.ty.zenxl.repository.PasscodeRepository;
import com.ty.zenxl.repository.RoleRepository;
import com.ty.zenxl.repository.UserRepository;
import com.ty.zenxl.request.ChangePasswordRequest;
import com.ty.zenxl.request.LoginRequest;
import com.ty.zenxl.request.SignUpRequest;
import com.ty.zenxl.response.UserResponse;

import lombok.RequiredArgsConstructor;

/**
 * Defines the mechanisms to check whether {@code User} is authenticated or not.
 * Else throws {@code AuthenticationException}
 * 
 * Sends passcodes through emails for the forgot password functionality using
 * {@code JavaMailSender}
 * 
 * Admin registration for the first time logic is implemented.
 * 
 * @author Indrajit
 * @version 1.0
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ZenxlAuthService {

	private final ZenxlCustomUserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasscodeRepository passcodeRepository;
	private final JwtUtils jwtUtils;
	private final JavaMailSender emailSender;

	private PasswordEncoder encoder = new BCryptPasswordEncoder();

	public String authenticateUser(LoginRequest request) throws AuthenticationException {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(INCORRECT_EMAIL_AND_PASSWORD);
		} catch (LockedException e) {
			throw new LockedException(ACCOUNT_IS_CURRENTLY_LOCKED);
		} catch (DisabledException e) {
			throw new DisabledException(ACCOUNT_IS_CURRENTLY_INACTIVE);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

		final String generateJwtToken = jwtUtils.generateToken(userDetails);
		
		return generateJwtToken;
	}

	public String forgotPassword(String email) {

		Optional<User> findByEmail = userRepository.findByEmail(email);
		if (Boolean.FALSE.equals(findByEmail.isPresent())) {
			throw new UserNotFoundException(USER_NOT_FOUND_WITH_THIS_EMAIL_ADDRESS);
		}

		try {
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setTo(findByEmail.get().getEmail());
			mimeMessageHelper.setSubject("Reset Your ZeNXL Password");
			int passcode = new Random().nextInt(900000) + 100000;

			Passcode builtPasscode = Passcode.builder().passcode(passcode).email(findByEmail.get().getEmail())
					.timestamp(LocalDateTime.now().plus(Duration.of(2, ChronoUnit.MINUTES))).build();

			Optional<Passcode> findByUsernameInPasscode = passcodeRepository.findByEmail(findByEmail.get().getEmail());
			if (findByUsernameInPasscode.isPresent()) {
				Passcode initialPasscode = findByUsernameInPasscode.get();
				initialPasscode.setPasscode(passcode);
				initialPasscode.setTimestamp(LocalDateTime.now().plus(Duration.of(5, ChronoUnit.MINUTES)));
				passcodeRepository.save(initialPasscode);
			} else {
				passcodeRepository.save(builtPasscode);
			}

			mimeMessageHelper.setText("\n" + "Hello " + findByEmail.get().getUsername() + "," + "\n\n"
					+ "Please use this passcode to reset your password " + passcode + "\n\n" + "Note" + "\n"
					+ "Passcode will expire in 5 minutes." + "\n\n" + "Thanks & Regards," + "\n" + "Team ZeNXL");
			sendEmail(mimeMessage);
		} catch (MailException | MessagingException e) {
			throw new EmailInterruptionException(e.getMessage());
		}
		return PASSCODE_HAS_BEEN_SENT;
	}

	@Async
	private void sendEmail(MimeMessage mimeMessage) {
		emailSender.send(mimeMessage);
	}

	public String changePassword(String email,ChangePasswordRequest request) {
		Optional<Passcode> passcode = passcodeRepository.findByEmail(email);
		if (passcode.isPresent()) {

			if (request.getPasscode() == (passcode.get().getPasscode())
					&& passcode.get().getTimestamp().isAfter(LocalDateTime.now())) {

				if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
					throw new ChangePasswordException(BOTH_PASSWORDS_SHOULD_BE_SAME);
				}

				Optional<User> findByEmail = userRepository.findByEmail(email);
				User user;
				if (findByEmail.isPresent()) {
					user = findByEmail.get();

					if (encoder.matches(request.getPassword(), user.getPassword())) {
						throw new ChangePasswordException(OLD_PASSWORD_AND_NEW_PASSWORD_SHOULD_BE_DIFFERENT);
					}

					user.setPassword(encoder.encode(request.getPassword()));
					userRepository.save(user);

					return PASSWORD_RESET_SUCCESSFUL;
				}
				throw new ChangePasswordException(USER_DOESN_T_EXIST_WITH_THE_ENTERED_EMAIL);
			}
			throw new ChangePasswordException(ENTERED_PASSCODE_NOT_VALID);
		}
		throw new ChangePasswordException(PASSCODE_NOT_FOUND_WITH_ENTERED_EMAIL);
	}

	public UserResponse adminRegistration(@Valid SignUpRequest request) {

		if (userRepository.findAll().isEmpty()) {
			

			String requestedRole = request.getRole();
			if (!requestedRole.startsWith("ROLE_")) {
				requestedRole = "ROLE_"+requestedRole.toUpperCase();
			}
			
			if (requestedRole.equals("ROLE_ADMIN")) {
				
				Role role = roleRepository.findByRoleName(requestedRole)
						.orElse(Role.builder().roleName(requestedRole).build());

				User user = User.builder().username(request.getUsername()).email(request.getEmail())
						.dateOfBirth(request.getDateOfBirth()).gender(request.getGender())
						.password(encoder.encode(request.getPassword())).active(Boolean.TRUE).role(role).build();

				User savedUser = userRepository.save(user);

				if (savedUser.getUsername() != null) {

					return UserResponse.builder().userId(savedUser.getUserId()).username(savedUser.getUsername()).status(Boolean.TRUE).build();
				}
				throw new UserPersistenceException(SIGN_UP_UNSUCCESSFUL);
			}
			throw new UserPersistenceException(THE_FIRST_REGISTERED_USER_MUST_BE_ADMIN_ONLY);
		}
		throw new UserPersistenceException(ONLY_REGISTERED_ADMIN_CAN_ADD_USERS);
	}
}
