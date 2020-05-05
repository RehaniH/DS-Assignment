package com.ds.restservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String email;
	private String mobile;
	
	
	
}
