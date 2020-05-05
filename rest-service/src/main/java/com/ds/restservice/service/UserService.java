package com.ds.restservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.restservice.dto.UserResponseDto;
import com.ds.restservice.models.User;
import com.ds.restservice.respositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserResponseDto findByUsername(String username) throws Exception {
		
		Optional<User> user = this.userRepository.findByUsername(username);
		
		if(!user.isPresent()) {
			throw new Exception();
		}
		
		UserResponseDto response = buildUserResponseDto(user.get());
		return response;
	}

	private UserResponseDto buildUserResponseDto(User user) {
		return UserResponseDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.email(user.getEmail())
				.mobile(user.getMobile())
				.build();
	}
}
