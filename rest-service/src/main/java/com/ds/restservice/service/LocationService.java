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

	/**
	 * Create new location providing room no and floor no
	 * @param req
	 * @return
	 */
	public LocationResponseDto create(LocationRequestDto req) {

		Location location = new Location();
		location.setFloorNo(req.getFloorNo());
		location.setRoomNo(req.getRoomNo());
		Location savedLocation = this.locationRepository.save(location);
		LocationResponseDto reponseDto = this.buildLocationReponseDto(savedLocation);
		return reponseDto;
	}

	/**
	 * Update location information by location id
	 * @param req
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * Get location object by Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * Find location details by roomNo and floor no
	 * @param roomNo
	 * @param floorNo
	 * @return
	 */
	public Optional<Location> findByRoomNoAndFloor(int roomNo, int floorNo) {
		
		Optional<Location> location = this.locationRepository.findByRoomNoAndFloorNo(roomNo, floorNo);
		return location;
		
	}
	
	/**
	 * Retreive all location information
	 * @return
	 */
	public List<LocationResponseDto> getAll(){
		List<LocationResponseDto> reponseList = new ArrayList<>();
		List<Location> locations = this.locationRepository.findAll();
		
		for (Location location : locations) {
			LocationResponseDto reponseDto = buildLocationReponseDto(location);
			reponseList.add(reponseDto);
		}
		
		return reponseList;
		
	}

	//build LocationResponseDto from Location entity object
	private LocationResponseDto buildLocationReponseDto(Location location) {
		
		return LocationResponseDto.builder()
				.id(location.getId())
				.floorNo(location.getFloorNo())
				.roomNo(location.getRoomNo())
				.build();
		
	}
}
