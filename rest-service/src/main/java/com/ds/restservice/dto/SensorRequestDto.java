package com.ds.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SensorRequestDto {

	@JsonProperty(value = "smoke_level")
	private Integer smokeLevel;
	
	@JsonProperty(value = "co2_level")
	private Integer co2Level;
	
	@JsonProperty(value = "location")
	private LocationRequestDto location;
	
	@JsonProperty(value = "active")
	private Boolean active;
}
