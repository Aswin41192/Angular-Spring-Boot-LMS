package com.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dto.MultiUserResponseDTO;
import com.lms.dto.UserRequestDTO;
import com.lms.dto.UserResponseDTO;
import com.lms.entity.UserEntity;
import com.lms.mapper.UserMapper;
import com.lms.repository.UserRepository;

@Service
public class UserServiceInterfaceImpl implements UserServiceInterface {

	
	private UserRepository userRepository;
	private UserMapper userMapper;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceInterfaceImpl(UserRepository userRepository,UserMapper userMapper,PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		userRequestDTO.setUserId(UUID.randomUUID().toString());
		userRequestDTO.setRawPassword(userRequestDTO.getPassword());
		userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		UserEntity user = userMapper.convertDTOToEntity(userRequestDTO);
		return userMapper.convertEntityToDTO(userRepository.save(user));
	}


	@Override
	public MultiUserResponseDTO findAll(int pageNumber,int maxSize) {
		MultiUserResponseDTO response = new MultiUserResponseDTO();
		List<UserResponseDTO> allUsers =new ArrayList<UserResponseDTO>();
		Pageable pageable = PageRequest.of(pageNumber, maxSize, Sort.by("firstName"));
		Page<UserEntity> allUsersEntity = userRepository.findAll(pageable);
		allUsersEntity.getContent().forEach(userEntity->allUsers.add(userMapper.convertEntityToDTO(userEntity)));
		response.setTotalCount(allUsersEntity.getTotalElements());
		response.setUsers(allUsers);
		return response;
	}

}
