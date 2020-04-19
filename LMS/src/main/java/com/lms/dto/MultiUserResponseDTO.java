package com.lms.dto;

import java.util.ArrayList;
import java.util.List;

public class MultiUserResponseDTO {

	private long totalCount;
	private List<UserResponseDTO> users =new ArrayList<>();
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public List<UserResponseDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserResponseDTO> users) {
		this.users = users;
	}
	
	
}
