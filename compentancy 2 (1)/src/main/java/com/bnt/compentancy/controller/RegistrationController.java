package com.bnt.compentancy.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.entity.Role;
import com.bnt.compentancy.entity.User;
import com.bnt.compentancy.entity.UserRole;
import com.bnt.compentancy.exception.UserAlreadyExistsException;
import com.bnt.compentancy.model.UserDetail;
import com.bnt.compentancy.service.UserService;

@RestController
@CrossOrigin("*")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	

	@PostMapping("/registerUser")
	public ResponseEntity<?> addCustomer(@RequestBody UserDetail userDetail) {
		
		try {
			System.out.println(userDetail);
			User user = transformerOfUserDetails(userDetail);
			System.out.println(user);
			Set<UserRole> set = getUserRoleFromUserDetails(userDetail,user);
			System.out.println(set);
			
			if (userService.isUserExists(user.getFirstName())) {
				throw new UserAlreadyExistsException("User already exists", HttpStatus.FOUND);
			} else {
				System.out.println("In the user Service");
				User userInDb = userService.createUser(user, set);
				return new ResponseEntity<>(userInDb, HttpStatus.CREATED);
			}
		} catch (UserAlreadyExistsException ex) {
			System.out.println(ex);
			return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
		} catch (Exception ex) {
			System.out.println(ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Set<UserRole> getUserRoleFromUserDetails(UserDetail userDetail,User user) {
		Set<UserRole> userRole = new HashSet<>();
		if (userDetail.getRoles().equalsIgnoreCase("Employee")) {
			Role role = new Role();
			UserRole userrole = new UserRole();

			role.setRoleID(1L);
			role.setRoleName("Employee");

			userrole.setRole(role);
			userrole.setUser(user);
			userRole.add(userrole);
		} else {
			Role role = new Role();
			UserRole userrole = new UserRole();

			role.setRoleID(2L);
			role.setRoleName("Manager");

			userrole.setRole(role);
			userrole.setUser(user);
			userRole.add(userrole);
		}
		 
		return userRole;
	}

	private User transformerOfUserDetails(UserDetail userDetail) {
		User user = new User();
		
		
	    String userName = userDetail.getEmail(); 
		 
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setEmail(userDetail.getEmail());
		user.setMobileNo(userDetail.getMobileNo());
		user.setPassword(userDetail.getPassword());
		user.setConfirmPassword(userDetail.getConfirmPassword());
		user.setEmployeeId(userDetail.getEmployeeId());
		
		

		return user;
	}

	@GetMapping("/getAllUser")
	public @ResponseBody ResponseEntity<List<User>> getAllCustomerList() {
		ResponseEntity<List<User>> responseEntity = null;
		try {
			List<User> customerDetails = userService.fetchUserList();
			responseEntity = new ResponseEntity<List<User>>(customerDetails, HttpStatus.OK);
		} catch (Exception ex) {
			responseEntity = new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
