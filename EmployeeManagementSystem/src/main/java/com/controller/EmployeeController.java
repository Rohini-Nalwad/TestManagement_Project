package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Employee;
import com.entity.Tests;
import com.exception.EmployeeNotFoundException;
import com.service.EmployeeService;
import com.service.TestService;

@RestController
@RequestMapping("/employee-management/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private TestService testService;

    @PostMapping
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.register(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            throw new EmployeeNotFoundException("Error occurred while getting employee by ID: " + employeeId);
        }
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeDetails);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/assign-test/{employeeId}/{testId}")
    public ResponseEntity<String> assignTestToEmployee(@PathVariable("employeeId") Long employeeId, @PathVariable("testId") Long testId) {

        Employee employee = employeeService.getEmployeeById(employeeId);
        Tests test = testService.getTestById(testId);
        
        employee.getAssignedTests().add(test);

        employeeService.updateEmployee(employee);

        return ResponseEntity.ok("Test assigned to employee successfully");
    }
}


