package com.ds.restservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@TypeAlias(value = "Location")
@Document
public class Location {

	@Id
	private String id;
	
	@Field("room")
	private int roomNo;
	
	@Field("floor")
	private int floorNo;
}
