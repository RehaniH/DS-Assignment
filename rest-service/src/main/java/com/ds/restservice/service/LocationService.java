package com.ds.restservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.restservice.dto.LocationRequestDto;
import com.ds.restservice.dto.LocationResponseDto;
import com.ds.restservice.models.Location;
import com.ds.restservice.respositories.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public LocationResponseDto create(LocationRequestDto req) {

		Location location = new Location();
		location.setFloorNo(req.getFloorNo());
		location.setRoomNo(req.getRoomNo());
		Location savedLocation = this.locationRepository.save(location);
		LocationResponseDto reponseDto = this.buildLocationReponseDto(savedLocation);
		return reponseDto;
	}


	public LocationResponseDto update(LocationRequestDto req, String id) throws Exception {

		Optional<Location> locationOpt = this.locationRepository.findById(id);
		
		if(!locationOpt.isPresent()) {
			throw new Exception();
		}
		Location location = locationOpt.get();
		location.setFloorNo(req.getFloorNo());
		location.setRoomNo(req.getRoomNo());
		Location savedLocation = this.locationRepository.save(location);
		LocationResponseDto reponseDto = this.buildLocationReponseDto(savedLocation);
		
		return reponseDto;
	}
	
	public LocationResponseDto getById(String id) throws Exception {
		
		Optional<Location> locationOpt = this.locationRepository.findById(id);
		LocationResponseDto locationResponseDto = new LocationResponseDto();
		
		if(locationOpt.isPresent()) {
			throw new Exception();
		}
		Location location = locationOpt.get();
		locationResponseDto.setId(location.getId());
		locationResponseDto.setFloorNo(location.getFloorNo());
		locationResponseDto.setRoomNo(location.getRoomNo());
		return locationResponseDto;
	}
	
	public Optional<Location> findByRoomNoAndFloor(int roomNo, int floorNo) {
		
		Optional<Location> location = this.locationRepository.findByRoomNoAndFloorNo(roomNo, floorNo);
		return location;
		
	}
	
	public List<LocationResponseDto> getAll(){
		List<LocationResponseDto> reponseList = new ArrayList<>();
		List<Location> locations = this.locationRepository.findAll();
		
		for (Location location : locations) {
			LocationResponseDto reponseDto = buildLocationReponseDto(location);
			reponseList.add(reponseDto);
		}
		
		return reponseList;
		
	}


	private LocationResponseDto buildLocationReponseDto(Location location) {
		
		return LocationResponseDto.builder()
				.id(location.getId())
				.floorNo(location.getFloorNo())
				.roomNo(location.getRoomNo())
				.build();
		
	}
}
