package com.ty.zenxl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ty.zenxl.service.ZenxlCustomUserDetailsService;

/**
 * <p>
 * Security configuration class for the application. Extends the
 * {@link WebSecurityConfigurerAdapter} class to override basic security
 * mechanism.
 * </p>
 * Permits the swagger api request calls.
 * 
 * Adds {@link CustomOncePerRequestFilter} to intercepts the request to validate
 * those.
 * 
 * Used {@link BCryptPasswordEncoder} to encode the plaintext passwords into
 * secured hashcodes.
 * 
 * Used {@link ZenxlCustomUserDetailsService} to load the authenticated user.
 * <p>
 * Implemented Role Based Authentication Mechanism for the application.
 * </p>
 * 
 * @author Indrajit
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String[] SWAGGER_WHITELIST = { "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", };

	@Autowired
	private ZenxlCustomUserDetailsService userDetailsService;

	@Bean
	public CustomOncePerRequestFilter getOncePerRequestFilter() {
		return new CustomOncePerRequestFilter();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
				.antMatchers(SWAGGER_WHITELIST).permitAll()
				.antMatchers("/api/v1/zenxl/auth/**").permitAll()
				.antMatchers("/api/v1/zenxl/user/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/api/v1/zenxl/customer/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("api/v1/zenxl/status/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("api/v1/zenxl/utility/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/api/v1/zenxl/item/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("api/v1/zenxl/import/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("api/v1/zenxl/export/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated().and()
				.formLogin();
		
		http.addFilterBefore(getOncePerRequestFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
