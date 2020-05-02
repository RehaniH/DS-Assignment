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
public class SensorResponseDto {
	@JsonProperty(value = "id")
	private String id;
	
	@JsonProperty(value = "smoke_level")
	private int smokeLevel;
	
	@JsonProperty(value = "co2_level")
	private int co2Level;
	
	@JsonProperty(value = "location")
	private LocationResponseDto location;
	
	@JsonProperty(value = "active")
	private Boolean active;
	
	@JsonProperty(value = "error")
	private Integer errorCode;
	
}
