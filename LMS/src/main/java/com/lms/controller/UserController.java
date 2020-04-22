package com.lms.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lms.dto.MultiUserResponseDTO;
import com.lms.dto.UserRequestDTO;
import com.lms.dto.UserResponseDTO;
import com.lms.service.UserServiceInterface;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServiceInterface userServiceInterfaceImpl;

	@PostMapping("/signup")
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
		UserResponseDTO createdUser = userServiceInterfaceImpl.createUser(userRequestDTO);
		return new ResponseEntity<UserResponseDTO>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<MultiUserResponseDTO> findAllUsers(@RequestParam(name = "page", defaultValue="0") int page,
			@RequestParam(name = "size", defaultValue="5") int size){
			MultiUserResponseDTO users = userServiceInterfaceImpl.findAll(page, size);
			return new ResponseEntity<MultiUserResponseDTO>(users,HttpStatus.OK);
	}
	
}
