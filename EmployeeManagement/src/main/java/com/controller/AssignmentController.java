package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.entity.AssignmentRequestDTO;
import com.entity.Employee;
import com.entity.Tests;
import com.service.EmployeeService;
import com.service.TestService;

@RestController
public class AssignmentController {

    private final EmployeeService employeeService;
    private final TestService testService;

    public AssignmentController(EmployeeService employeeService, TestService testService) {
        this.employeeService = employeeService;
        this.testService = testService;
    }

    @PostMapping("/assign-test")
    public ResponseEntity<AssignmentRequestDTO> assignTestToEmployee(@RequestBody AssignmentRequestDTO requestDTO) {
        long employeeId = requestDTO.getEmployeeId();
        long testId = requestDTO.getTestId();

        Employee employee = employeeService.getEmployeeById(employeeId);
        Tests test = testService.getTestById(testId);
        
        employee.getAssignedTests().add(test);

        employeeService.updateEmployee(employee);

        return ResponseEntity.ok(requestDTO);
    }
}
