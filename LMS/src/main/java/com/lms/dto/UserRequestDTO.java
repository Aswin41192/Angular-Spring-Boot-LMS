package com.lms.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestDTO {

	private String userId;
	
	@NotBlank(message = "First Name should not be blank!")
	private String firstName;
	
	@NotBlank(message = "Last Name should not be blank!")
	private String lastName;
	
	@NotBlank(message = "EMail Should not be blank!")
	@Email(message = "Not a Valid EMail")
	private String email;
	
	@Size(min = 6,max=16,message = "Password should be greater than 6 characters long and less than 16 characters long")
	private String password;
	
	private String roles;
	
	private String rawPassword;
	
	public UserRequestDTO() {
		this.roles = "USER";
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getRawPassword() {
		return rawPassword;
	}
	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}
	
}
