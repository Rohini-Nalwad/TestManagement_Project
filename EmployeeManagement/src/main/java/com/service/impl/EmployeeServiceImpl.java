package com.service.impl;

import com.entity.Employee;
import com.exception.DuplicateEmployeeException;
import com.exception.EmployeeNotFoundException;
import com.exception.InvalidCredentialsException;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

//	@Autowired
//  private RestTemplate restTemplate;
//
//  // URL of the testManagement microservice
//  private static final String TEST_MANAGEMENT_URL = "http://testmanagement-service/api/v1/tests";
//
//  public Tests[] getTestsForEmployee(Long employeeId) {
//      // Make an HTTP GET request to the testManagement microservice
//      Tests[] tests = restTemplate.getForObject(TEST_MANAGEMENT_URL + "?employeeId={employeeId}", Tests[].class, employeeId);
//      return tests;
//  }
	
 @Autowired
 private EmployeeRepository employeeRepository;

 @Override
 public Employee register(Employee employee) {
     if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
         throw new DuplicateEmployeeException("Employee with email '" + employee.getEmail() + "' already exists");
     }
     return employeeRepository.save(employee);
 }

 @Override
 public Employee login(String email, String password) {
     // Retrieve the employee from the database based on the provided email
     Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
     
     if (optionalEmployee.isPresent()) {
         Employee employee = optionalEmployee.get();
            
           if (employee.getPassword().equals(password)) {
             
             return employee;
         } else {
             // Passwords don't match, throw an exception or return null
             throw new InvalidCredentialsException("Invalid email or password");
         }
     } else {
         // Employee with the provided email doesn't exist, throw an exception or return null
         throw new EmployeeNotFoundException("Employee with email '" + email + "' not found");
     }
 }

 @Override
 public List<Employee> getAllEmployees() {
     return employeeRepository.findAll();
 }

 @Override
 public Employee getEmployeeById(long employeeId) {
     return employeeRepository.findById(employeeId)
             .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));
 }

 @Override
 public Employee updateEmployee(Employee employeeDetails) {
     Employee employee = employeeRepository.findById(employeeDetails.getEmployeeId())
             .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeDetails.getEmployeeId()));

     employee.setFirstName(employeeDetails.getFirstName());
     employee.setLastName(employeeDetails.getLastName());
     employee.setEmail(employeeDetails.getEmail());
     employee.setPassword(employeeDetails.getPassword());

     return employeeRepository.save(employee);
 }

 @Override
 public void deleteEmployee(long employeeId) {
     Employee employee = employeeRepository.findById(employeeId)
             .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));
     employeeRepository.delete(employee);
 }
}
  