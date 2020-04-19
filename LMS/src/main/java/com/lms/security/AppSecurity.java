package com.lms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lms.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class AppSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment environment;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsServiceImpl(userRepository)).passwordEncoder(getPasswordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(getFilter())
        .addFilterBefore(new JWTAuthorizationFilter(getAuthManager(),userRepository,environment),UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/**/signup").permitAll()
        .antMatchers("/users/findAll").hasRole("ADMIN")
        .anyRequest().authenticated();
	}
	
	private AuthenticationFilter getFilter() throws Exception {
		AuthenticationFilter filter = new AuthenticationFilter(getAuthManager(),userRepository,environment);
		return filter;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager getAuthManager() throws Exception {
		return super.authenticationManagerBean();
	}
}
