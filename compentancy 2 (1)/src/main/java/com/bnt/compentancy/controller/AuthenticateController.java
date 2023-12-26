package com.bnt.compentancy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.config.JwtUtil;
import com.bnt.compentancy.entity.User;
import com.bnt.compentancy.model.JwtRequest;
import com.bnt.compentancy.model.JwtResponce;
import com.bnt.compentancy.model.UserDetail;
import com.bnt.compentancy.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager autheticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generate-token")
	private ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not Found");
		}
		
		 UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		 String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponce(token));
		
	}
	
	
	private void authenticate(String username,String password) throws Exception
	{
		try {
			autheticationManager
			   .authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) {
			throw new Exception("User Disabled "+e.getMessage());
		}catch(BadCredentialsException be) {
			throw new Exception("Invalid Credentials" +be.getMessage());
		}
		
	}
	
	//
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principle) {
	
		return 	((User) this.userDetailsService.loadUserByUsername(principle.getName()));
		
	}

}
