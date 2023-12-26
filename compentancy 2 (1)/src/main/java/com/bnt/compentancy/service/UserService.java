package com.bnt.compentancy.service;

import java.util.List;
import java.util.Set;

import com.bnt.compentancy.entity.User;
import com.bnt.compentancy.entity.UserRole;

public interface UserService {

	User saveUser(User user);

	List<User> fetchUserList();

	public boolean isUserExists(String firstName);
	
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
