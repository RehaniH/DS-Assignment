package com.ds.restservice.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ds.restservice.models.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, String>{

	Optional<Location> findByRoomNoAndFloorNo(int roomNo, int floorNo);
	Optional<Location> findById(String id);
	List<Location> findAll();
	
}
