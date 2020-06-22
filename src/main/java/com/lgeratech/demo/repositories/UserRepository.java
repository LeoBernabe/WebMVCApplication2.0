package com.lgeratech.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lgeratech.demo.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUserName(String userName);

}
