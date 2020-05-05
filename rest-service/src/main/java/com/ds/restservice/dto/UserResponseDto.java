package com.ds.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {


	private String id;
	private String username;
	private String password;
	private String email;
	private String mobile;
	
	@JsonProperty(value = "error_code")
	private Integer errorCode;
}
