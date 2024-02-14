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
     Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
     
     if (optionalEmployee.isPresent()) {
         Employee employee = optionalEmployee.get();
            
           if (employee.getPassword().equals(password)) {
             
             return employee;
         } else {
             throw new InvalidCredentialsException("Invalid email or password");
         }
     } else {
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
  