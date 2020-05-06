package com.ds.restservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
@TypeAlias(value = "User")
public class User {

	@Id
	private String id;
	
	private String username;
	
	private String password;
	
	@Field("sender_email")
	private String senderEmail;
	
	@Field("receiver_email")
	private String recieverEmail;
	
	@Field("sender_password")
	private String senderPassword;
	
	@Field("sender_mobile")
	private String senderMobile;
	
	@Field("receiver_mobile")
	private String recieverMobile;
	
	
	
}
