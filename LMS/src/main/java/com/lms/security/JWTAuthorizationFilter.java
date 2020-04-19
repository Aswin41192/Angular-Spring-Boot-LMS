package com.lms.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lms.entity.UserEntity;
import com.lms.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private Environment environment;
	private UserRepository userRepository;
	private AuthenticationManager authManager;

	public JWTAuthorizationFilter(AuthenticationManager authManager,UserRepository userRepository,Environment environment) {
		super();
		this.environment = environment;
		this.userRepository = userRepository;
		this.authManager = authManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getHeader("Authorization") != null) {
			String token = request.getHeader("Authorization").replace("Bearer", "");
			Claims claims = Jwts.parser().setSigningKey(environment.getProperty("token.secret").getBytes()).parseClaimsJws(token)
						.getBody();
			Date expiryDate = claims.getExpiration();
			Date currentDate = new Date();
			if (currentDate.before(expiryDate)) {
				String userId = claims.getSubject();
				Optional<UserEntity> optionalUser = userRepository.findByUserId(userId);
				if (optionalUser.isPresent()) {
					UserEntity user = optionalUser.get();
					try {
						List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
						roles.add(new SimpleGrantedAuthority(user.getRoles()));
						Authentication authentication = authManager.authenticate(
								new UsernamePasswordAuthenticationToken(user.getEmail(),user.getRawPassword()));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					} catch (BadCredentialsException e) {
						response.setStatus(401);
						response.getWriter().write("Bad Credentials");
					}
				}
			}
		} 
		filterChain.doFilter(request, response);
	}

}
