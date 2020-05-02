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
public class LocationRequestDto {

	@JsonProperty(value = "room_no")
	private Integer roomNo;

	@JsonProperty(value = "floor_no")
	private Integer floorNo;
}
