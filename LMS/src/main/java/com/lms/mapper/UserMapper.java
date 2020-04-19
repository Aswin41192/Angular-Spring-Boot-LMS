package com.lms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.lms.dto.UserRequestDTO;
import com.lms.dto.UserResponseDTO;
import com.lms.entity.UserEntity;

@Component
public class UserMapper extends Mapper {
	
	private ModelMapper mapper;
	
	public UserMapper() {
		this.mapper = super.getMapper();
	}
	
	
	public  UserResponseDTO convertEntityToDTO(UserEntity userEntity) {
		return mapper.map(userEntity, UserResponseDTO.class);
	}
	
	public  UserEntity convertDTOToEntity(UserRequestDTO userRequestDTO) {
		return mapper.map(userRequestDTO, UserEntity.class);
	}
}
