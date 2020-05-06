package com.ds.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocationResponseDto {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "room_no")
	private int roomNo;

	@JsonProperty(value = "floor_no")
	private int floorNo;
	
}
