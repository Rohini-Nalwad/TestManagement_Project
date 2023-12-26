package com.bnt.compentancy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.compentancy.dao.UserJpaRepository;
import com.bnt.compentancy.entity.User;

@Service
public class UserLoginImpl {

	
    @Autowired
    private UserJpaRepository userJpaRepository;
	
	public User getUserDetails(String email,String password){
		boolean isverified=false;
		User user=userJpaRepository.findByEmail(email);
		if (user == null) {
			System.out.println("user Not found for entered email");
        }
		
		if(user.getPassword().equals(password)){
        	return user;
			
        }else{
			
        }
		return user; 
	}
	
}
