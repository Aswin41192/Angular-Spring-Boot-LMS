package com.lms.service;

import com.lms.dto.MultiUserResponseDTO;
import com.lms.dto.UserRequestDTO;
import com.lms.dto.UserResponseDTO;

public interface UserServiceInterface {

	UserResponseDTO createUser(UserRequestDTO userRequestDTO);
	MultiUserResponseDTO findAll(int pageNumber,int maxSize);
}
