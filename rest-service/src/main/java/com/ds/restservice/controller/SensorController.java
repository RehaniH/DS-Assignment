package com.ds.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.restservice.dto.LocationRequestDto;
import com.ds.restservice.dto.SensorRequestDto;
import com.ds.restservice.dto.SensorResponseDto;
import com.ds.restservice.service.SensorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("v1/sensors")
public class SensorController {

	@Autowired
	private SensorService sensorService;
	/**
	 * Register a new sensor 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "create new sensor")
	@PostMapping
	public SensorResponseDto register(@RequestBody LocationRequestDto request) {
		
		SensorResponseDto responseDto = this.sensorService.register(request);
		return responseDto;
	}
	
	/***
	 * update sensor information 
	 * @param location_id
	 * @param id
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "update sensor information")
	@PutMapping("/{id}/locations/{location_id}")
	public SensorResponseDto update(
			@ApiParam(name = "location_id") @PathVariable String location_id,
			@ApiParam(name = "id") @PathVariable String id,
			@RequestBody SensorRequestDto request) {
		
		SensorResponseDto responseDto = null;
		try {
			responseDto = this.sensorService.update(request, id, location_id);
		} catch (Exception e) {
			responseDto = new SensorResponseDto();
			responseDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.ordinal());
		}
		return responseDto;
	}
	
	/**
	 * Update sensor status
	 * @param id
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "update sensor status")
	@PutMapping("/{id}")
	public SensorResponseDto update(
			@ApiParam(name = "id") @PathVariable String id,
			@RequestBody SensorRequestDto request) {
		
		SensorResponseDto responseDto = null;
		try {
			responseDto = this.sensorService.updateStatus(request, id);
		} catch (Exception e) {
			responseDto = new SensorResponseDto();
			responseDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.ordinal());
		}
		return responseDto;
	}
	
	/**
	 * Get sensor details by id
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "get sensor details by id")
	@GetMapping("/{id}")
	public SensorResponseDto getById(
			@ApiParam(name = "id") @PathVariable String id) {
		
		SensorResponseDto responseDto = null;
		try {
			responseDto = this.sensorService.getById(id);
		} catch (Exception e) {
			responseDto = new SensorResponseDto();
			responseDto.setErrorCode(HttpStatus.NOT_FOUND.ordinal());	
		}
		return responseDto;
	}
	
	/**
	 * Get all sensor information
	 * @return
	 */
	@ApiOperation(value = "get all sensor information")
	@GetMapping
	public List<SensorResponseDto> readAll() {
		
		List<SensorResponseDto> responseList = null;
		try {
			responseList = this.sensorService.getAll();
			return responseList;
		} catch (Exception e) {
			return null;
		}
		
	}
}
