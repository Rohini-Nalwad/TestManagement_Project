package com.bnt.compentancy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.entity.MCQTest;
import com.bnt.compentancy.entity.MCQTestResult;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	
	@PostMapping("/creatTest")
    public String createTest(@RequestBody MCQTest mcqTest) {

		return "Test created successfully.";
    }

    @GetMapping("/viewResult")
    public List<MCQTestResult> viewResults() {
       System.out.println("viewResults method called");
        return null;
    }
}
