package com.service;

import com.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee register(Employee employee);
    Employee login(String email, String password);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long employeeId);
    Employee updateEmployee(Employee employeeDetails);
    void deleteEmployee(long employeeId);
}
