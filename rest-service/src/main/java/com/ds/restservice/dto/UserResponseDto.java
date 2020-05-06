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


	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "username")
	private String username;
	
	@JsonProperty(value = "password")
	private String password;
	
	@JsonProperty(value = "sender_email")
	private String senderEmail;
	
	@JsonProperty(value = "receiver_email")
	private String receiverEmail;
	
	@JsonProperty(value = "sender_password")
	private String senderPassword;
	
	@JsonProperty(value = "error_code")
	private Integer errorCode;
}
