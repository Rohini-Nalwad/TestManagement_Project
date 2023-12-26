package com.bnt.compentancy.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.compentancy.dao.RoleRepository;
import com.bnt.compentancy.dao.UserCrudRepository;
import com.bnt.compentancy.entity.User;
import com.bnt.compentancy.entity.UserRole;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserCrudRepository userCrudRepository;
	
	@Autowired
	private RoleRepository roleRepository;	

	public User saveUser(User user) {

		return userCrudRepository.save(user);
	}

	public List<User> fetchUserList() {

		return (List<User>) userCrudRepository.findAll();
	}

	public boolean isUserExists(String firstName) {
		return userCrudRepository.existsByFirstName(firstName);
	}

	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		/* User local = this.userCrudRepository.findByUsername(user.getUserName()); */
		
		/*
		 * if(local != null) { throw new Exception("User already present"); }
		 */
		
		for(UserRole ur  : userRoles) {
			roleRepository.save(ur.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		
		User local = userCrudRepository.save(user);
		return  local;
	}
}
