package com.ds.restservice.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ds.restservice.models.Location;
import com.ds.restservice.models.Sensor;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String>{

	Optional<Sensor> findById(String id);
	List<Sensor> findAll();
	Sensor findByLocation(Location location);
	List<Sensor> findByActive(boolean active);
	
	
}
