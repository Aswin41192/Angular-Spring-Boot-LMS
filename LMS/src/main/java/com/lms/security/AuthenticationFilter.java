package com.lms.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.dto.LoginRequestDTO;
import com.lms.entity.UserEntity;
import com.lms.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;
	private UserRepository userRepository;
	private Environment environment;
	
	public AuthenticationFilter(AuthenticationManager authManager,UserRepository userRepository,Environment environment) {
		super();
		this.authManager = authManager;
		this.userRepository = userRepository;
		this.environment = environment;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Authentication authentication = null;
		try {
			LoginRequestDTO loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
			Optional<UserEntity> optionalUser = userRepository.findByEmail(loginRequest.getUsername());
			if(optionalUser.isPresent()) {
				try {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(optionalUser.get().getRoles()));
				authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
						loginRequest.getPassword(),authorities));
				}catch(BadCredentialsException e) {
					response.getWriter().println("Bad Credentials");
					response.setStatus(400);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return authentication;
	}

	private String generateJWT(UserEntity user) {
		Map<String, Object> claims = new HashMap<String,Object>();
		claims.put("USER",user.getEmail());
		return Jwts.builder().setSubject(user.getUserId())
				.setClaims(claims)
				.setSubject(user.getUserId())
				.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(environment.getProperty("token.expiration"))))
				.setIssuedAt(new Date()).setIssuer("LMS APP").signWith(SignatureAlgorithm.HS256, environment.getProperty("token.secret").getBytes())
				.compact();
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDetails userDetails = (UserDetails)authResult.getPrincipal();
		String email = userDetails.getUsername();
		UserEntity user = userRepository.findByEmail(email).get();
		response.addHeader("TOKEN", generateJWT(user));
		response.setStatus(200);
		response.getWriter().println("Successfully Authenticated!!!");
		SecurityContextHolder.getContext().setAuthentication(authResult);
	}
}
