package com.ds.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.restservice.dto.LocationRequestDto;
import com.ds.restservice.dto.LocationResponseDto;
import com.ds.restservice.service.LocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@ApiOperation(value = "create new location")
	@PostMapping
	public LocationResponseDto createNewLocation(@RequestBody LocationRequestDto requestDto) {
		
		LocationResponseDto createdLocation = this.locationService.create(requestDto);
		return createdLocation;
	}
	
	@ApiOperation(value = "update location information")
	@PutMapping("/{id}")
	public LocationResponseDto updateLocation(
			@ApiParam(name = "id") @PathVariable String id,
			@RequestBody LocationRequestDto requestDto) {
		
		LocationResponseDto createdLocation;
		try {
			createdLocation = this.locationService.update(requestDto, id);
			return createdLocation;
		} catch (Exception e) {
			return null;
		}
	}
	
	@ApiOperation(value = "get location information by id")
	@GetMapping("/{id}")
	public LocationResponseDto getById(@ApiParam(name = "id")
			@PathVariable String id) {
		
		LocationResponseDto responseDto = null;
		
		try {
			responseDto = this.locationService.getById(id);
			return responseDto;
		} catch (Exception e) {
			return null;
		}
	}
	
	@ApiOperation(value = "get all location information")
	@GetMapping
	public List<LocationResponseDto> getAllLocations() {
		
		List<LocationResponseDto> responseDto = this.locationService.getAll();
		return responseDto;
		
	}
	
	
	
}
