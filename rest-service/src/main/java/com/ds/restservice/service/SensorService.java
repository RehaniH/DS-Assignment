package com.ds.restservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.restservice.dto.LocationRequestDto;
import com.ds.restservice.dto.LocationResponseDto;
import com.ds.restservice.dto.SensorRequestDto;
import com.ds.restservice.dto.SensorResponseDto;
import com.ds.restservice.models.Location;
import com.ds.restservice.models.Sensor;
import com.ds.restservice.respositories.SensorRepository;

@Service
public class SensorService {

	@Autowired
	private SensorRepository sensorRepository;

	@Autowired
	private LocationService locationService;

	/**
	 * register new sensor
	 * @param req
	 * @return
	 */
	public SensorResponseDto register(LocationRequestDto req) {

		int floorNo = req.getFloorNo();
		int roomNo = req.getRoomNo();
		Location location;
		
		//check if the location provided already exists in the database 
		Optional<Location> locationOpt = this.locationService.findByRoomNoAndFloor(roomNo, floorNo);

		if(locationOpt.isPresent()) {
			location = locationOpt.get();
		}else {
			//create new location object with room no and floor no
			LocationResponseDto createdLocation = this.locationService.create(req);
			location = getLocationObject(createdLocation);
		}

		Sensor sensor = new Sensor();
		sensor.setActive(true);
		sensor.setLocation(location);

		Sensor savedSensor = this.sensorRepository.save(sensor);
		SensorResponseDto reponseDto = buildSensorResponseDto(savedSensor);
		LocationResponseDto locationResponseDto = buildLocationReponseDto(savedSensor.getLocation());
		reponseDto.setLocation(locationResponseDto);
		return reponseDto;
	}



	/**
	 * Update sensor information and related location information (room no and floor no)
	 * @param req
	 * @param id
	 * @param locationId
	 * @return
	 * @throws Exception
	 */
	public SensorResponseDto update(SensorRequestDto req, String id, String locationId) throws Exception {

		Optional<Sensor> sensorOpt = this.sensorRepository.findById(id);

		if(!sensorOpt.isPresent()) {
			throw new Exception();
		}
		Sensor sensor = sensorOpt.get();

		Location location = null;

		if(req.getLocation() != null) {
			LocationRequestDto locationRequest = req.getLocation();
			
			//check if the location provided already exists in the database 
			Optional<Location> locationCheck = 
					this.locationService.findByRoomNoAndFloor(locationRequest.getRoomNo(), locationRequest.getFloorNo());
			
			
			if(locationCheck.isPresent()) {
				location = locationCheck.get();
			}else {
				//update existing location object with the new information provided
				LocationResponseDto updatedLocation = this.locationService.update(locationRequest, locationId);
				location = getLocationObject(updatedLocation);
			}
		}

		//sensor is set active by default
		sensor.setActive(req.getActive());
		sensor.setLocation(location != null ? location : sensor.getLocation());
		Sensor savedSensor = this.sensorRepository.save(sensor);
		SensorResponseDto reponseDto = buildSensorResponseDto(savedSensor);
		LocationResponseDto locationResponseDto = buildLocationReponseDto(savedSensor.getLocation());
		reponseDto.setLocation(locationResponseDto);
		return reponseDto;
	}

	/**
	 * Update sensor status by sensor id, i.e Carbon dioxide and smoke levels of a particular sensor
	 * @param req
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SensorResponseDto updateStatus(SensorRequestDto req, String id) throws Exception {

		Optional<Sensor> sensorOpt = this.sensorRepository.findById(id);

		if(!sensorOpt.isPresent()) {
			throw new Exception();
		}
		Sensor sensor = sensorOpt.get();
		sensor.setCo2Level(req.getCo2Level());
		sensor.setSmokeLevel(req.getSmokeLevel());
		
		Sensor savedSensor = this.sensorRepository.save(sensor);
		SensorResponseDto reponseDto = buildSensorResponseDto(savedSensor);
		LocationResponseDto locationResponseDto = buildLocationReponseDto(savedSensor.getLocation());
		reponseDto.setLocation(locationResponseDto);
		return reponseDto;
	}

	/**
	 * Retreive all sensor information from the database
	 * @return
	 */
	public List<SensorResponseDto> getAll() {
		
		List<Sensor> sensors = this.sensorRepository.findAll();
		List<SensorResponseDto> responseList = new ArrayList<>();
		
		
		for (Sensor sensor : sensors) {
			SensorResponseDto sensorResponseDto = buildSensorResponseDto(sensor);
			LocationResponseDto locationReponseDto = buildLocationReponseDto(sensor.getLocation());
			sensorResponseDto.setLocation(locationReponseDto);
			responseList.add(sensorResponseDto);
		}
		
		return responseList;
		
	}
	
	/**
	 * Retreive all active sensor information from the database
	 * @return
	 */
	public List<SensorResponseDto> getAllByActive() {
		
		List<Sensor> sensors = this.sensorRepository.findByActive(true);
		List<SensorResponseDto> responseList = new ArrayList<>();
		
		
		for (Sensor sensor : sensors) {
			SensorResponseDto sensorResponseDto = buildSensorResponseDto(sensor);
			LocationResponseDto locationReponseDto = buildLocationReponseDto(sensor.getLocation());
			sensorResponseDto.setLocation(locationReponseDto);
			responseList.add(sensorResponseDto);
		}
		
		return responseList;
		
	}
	
	/**
	 * Get sensor information by sensor id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SensorResponseDto getById(String id) throws Exception {
		Optional<Sensor> optional = this.sensorRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new Exception();
		}
		SensorResponseDto sensorResponseDto = buildSensorResponseDto(optional.get());
		LocationResponseDto locationReponseDto = buildLocationReponseDto(optional.get().getLocation());
		sensorResponseDto.setLocation(locationReponseDto);
		
		return sensorResponseDto;
		
	}

	// get a Location object from LocationResponseDto
	private Location getLocationObject(LocationResponseDto createdLocation) {
		Location location = new Location();
		location.setId(createdLocation.getId());
		location.setFloorNo(createdLocation.getFloorNo());
		location.setRoomNo(createdLocation.getRoomNo());
		return location;
	}

	// builds a Sensor response DTO 
	private SensorResponseDto buildSensorResponseDto(Sensor savedSensor) {
		return SensorResponseDto.builder()
				.id(savedSensor.getId())
				.active(savedSensor.isActive())
				.co2Level(savedSensor.getCo2Level())
				.smokeLevel(savedSensor.getSmokeLevel())
				.build();
	}

	// builds a Location response DTO 
	private LocationResponseDto buildLocationReponseDto(Location location) {

		return LocationResponseDto.builder()
				.id(location.getId())
				.floorNo(location.getFloorNo())
				.roomNo(location.getRoomNo())
				.build();

	}

}