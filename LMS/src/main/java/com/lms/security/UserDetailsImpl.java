package com.lms.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lms.entity.UserEntity;

public class UserDetailsImpl implements UserDetails {

	private UserEntity user;
	private static final long serialVersionUID = -7803594177896738257L;

	public UserDetailsImpl(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String[] roles = this.user.getRoles().split(",");
		Arrays.stream(roles).forEach(role->authorities.add(new SimpleGrantedAuthority(role)));
		Arrays.stream(roles).forEach(role->authorities.add(new SimpleGrantedAuthority("ROLE_"+role)));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !user.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !user.isLocked();
	}

	@Override
	public boolean isEnabled() {
		return user.isActive();
	}

}
