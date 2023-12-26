package com.bnt.compentancy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.entity.MCQTestResult;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@PostMapping("/takeTest")
    public String takeTest(@RequestBody MCQTestResult testResult) {

		return "Test taken successfully.";
    }
}
