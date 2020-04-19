package com.lms.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lms.entity.UserEntity;
import com.lms.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return new UserDetailsImpl(user);
	}

}
