package com.bnt.compentancy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bnt.compentancy.entity.User;

@Repository
public interface UserCrudRepository extends CrudRepository<User, Integer> {
	
	boolean existsByFirstName(String firstName);

	/* public User findByUsername(String userName); */

}
