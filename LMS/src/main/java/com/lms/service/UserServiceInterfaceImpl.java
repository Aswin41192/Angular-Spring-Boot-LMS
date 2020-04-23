package com.lms.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dto.MultiResponseDTO;
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
	public MultiResponseDTO<UserResponseDTO> findAll(int pageNumber,int maxSize) {
		pageNumber = pageNumber==0?0:pageNumber-1;
		MultiResponseDTO<UserResponseDTO> response = new MultiResponseDTO<UserResponseDTO>();
		Pageable pageable = PageRequest.of(pageNumber, maxSize, Sort.by("firstName"));
		Page<UserEntity> allUsersEntity = userRepository.findAll(pageable);
		List<UserResponseDTO> allUsers = allUsersEntity.getContent().stream().map(userMapper::convertEntityToDTO)
											.collect(Collectors.toList());
		response.setTotalCount(allUsersEntity.getTotalElements());
		response.setTotalPage(allUsersEntity.getTotalPages());
		response.setItems(allUsers);
		return response;
	}

}
