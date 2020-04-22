package com.lms.mapper;

import org.springframework.stereotype.Component;

import com.lms.dto.UserRequestDTO;
import com.lms.dto.UserResponseDTO;
import com.lms.entity.UserEntity;

@Component
public class UserMapper extends Mapper<UserEntity,UserRequestDTO,UserResponseDTO> {
	
	@Override
	public  UserResponseDTO convertEntityToDTO(UserEntity userEntity) {
		return getMapper().map(userEntity, UserResponseDTO.class);
	}
	
	@Override
	public  UserEntity convertDTOToEntity(UserRequestDTO userRequestDTO) {
		return getMapper().map(userRequestDTO, UserEntity.class);
	}
	
}
