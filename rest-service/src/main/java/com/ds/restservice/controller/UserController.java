package com.ds.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.restservice.dto.UserResponseDto;
import com.ds.restservice.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/***
	 * Get user details by username
	 * @param username
	 * @return
	 */
	@ApiOperation(value = "Get user details from username")
	@GetMapping("/{username}")
	public UserResponseDto findByUsername(@ApiParam(name = "username")
			@PathVariable String username) {
		UserResponseDto response = null;
		
		try {
			response = this.userService.findByUsername(username);
		} catch (Exception e) {
			response = new UserResponseDto();
			response.setErrorCode(HttpStatus.NOT_FOUND.ordinal());
		}
		return response;
	}

}
