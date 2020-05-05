package com.ds.restservice.respositories;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ds.restservice.models.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{

	Optional<User> findById(String id);
	Optional<User> findByUsername(String username);
}
