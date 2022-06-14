package com.ty.zenxl.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ty.zenxl.pojos.JwtUtils;
import com.ty.zenxl.service.ZenxlCustomUserDetailsService;

/**<p>
 * Implemented {@link OncePerRequestFilter} interface to provide the mechanishm
 * of intercepting the coming request calls and validate them using JWT token.
 * </p><p>
 * Once successful validation of the request, the user credentials are stored in
 * the {@code SecurityContextHolder} and then forward the filter chain.
 * </p>
 * 
 * @author Indrajit
 * @version 1.0
 */

public class CustomOncePerRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private ZenxlCustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (Boolean.TRUE.equals(jwtUtil.validateToken(jwt, userDetails))) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}
