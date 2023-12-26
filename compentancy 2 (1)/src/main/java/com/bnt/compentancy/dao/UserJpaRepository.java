package com.bnt.compentancy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnt.compentancy.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

	
}
