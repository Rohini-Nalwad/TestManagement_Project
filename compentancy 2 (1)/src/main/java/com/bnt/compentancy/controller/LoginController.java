package com.bnt.compentancy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.entity.LoginRequest;
import com.bnt.compentancy.entity.User;
import com.bnt.compentancy.model.UserDetail;
import com.bnt.compentancy.service.UserLoginImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private UserLoginImpl userLoginImpl;
	
	/*
	 * @Autowired UserDetails userdetails;
	 */

	@GetMapping("/userLogin")
    public  User getUserDetails(@RequestBody LoginRequest loginRequest) {
		ResponseEntity <String> responseEntity = null;
		String actualResponse=null;
		System.out.println(loginRequest);
		 User user=new User();
		try {
			user = userLoginImpl.getUserDetails(loginRequest.getUserName(), loginRequest.getPassword());
			
			responseEntity = new ResponseEntity<String>(actualResponse,HttpStatus.CREATED);
		}catch(Exception ex) {
			responseEntity =  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
       
		return user;
    }
}
