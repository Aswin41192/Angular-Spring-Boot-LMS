package com.lms.dto;

import java.util.ArrayList;
import java.util.List;

public class MultiUserResponseDTO  extends PaginationDTO{

	private List<UserResponseDTO> users =new ArrayList<>();
	
	public List<UserResponseDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserResponseDTO> users) {
		this.users = users;
	}
}
