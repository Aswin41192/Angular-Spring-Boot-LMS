package com.lms.service;

import com.lms.dto.MultiResponseDTO;
import com.lms.dto.UserRequestDTO;
import com.lms.dto.UserResponseDTO;

public interface UserServiceInterface {

	UserResponseDTO createUser(UserRequestDTO userRequestDTO);
	MultiResponseDTO<UserResponseDTO> findAll(int pageNumber,int maxSize);
}
