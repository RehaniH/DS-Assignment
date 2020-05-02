package com.ds.restservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
@TypeAlias(value = "Sensor")
@Document
public class Sensor {

	@Id
	private String id;
	
	@Field("smoke_level")
	private int smokeLevel;
	
	@Field("co2_level")
	private int co2Level;
	
	@DBRef 
	private Location location;
	
	private boolean active;
}
