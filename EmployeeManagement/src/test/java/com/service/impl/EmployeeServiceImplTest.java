package com.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.entity.Employee;
import com.exception.EmployeeNotFoundException;
import com.exception.InvalidCredentialsException;
import com.repository.EmployeeRepository;
import com.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() {
        String email = "test@example.com";
        String password = "password";

        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);

        when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(employee));

        Employee loggedInEmployee = employeeService.login(email, password);

        assertNotNull(loggedInEmployee);
        assertEquals(email, loggedInEmployee.getEmail());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        String email = "test@example.com";
        String password = "password";

        when(employeeRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.login(email, password);
        });
    }

    @Test
    public void testGetAllEmployees_Success() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john@example.com", "password", null));
        employees.add(new Employee(2L, "Jane", "Doe", "jane@example.com", "password", null));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> retrievedEmployees = employeeService.getAllEmployees();
        
        assertEquals(2, retrievedEmployees.size());
        assertEquals("John", retrievedEmployees.get(0).getFirstName());
        assertEquals("Jane", retrievedEmployees.get(1).getFirstName());
    }

    @Test
    public void testGetEmployeeById_Success() {
        long employeeId = 1L;
        Employee employee = new Employee(employeeId, "John", "Doe", "john@example.com", "password", null);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Employee retrievedEmployee = employeeService.getEmployeeById(employeeId);

        assertNotNull(retrievedEmployee);
        assertEquals("John", retrievedEmployee.getFirstName());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(employeeId);
        });
    }

    @Test
    public void testUpdateEmployee_Success() {
        Employee employee = new Employee(1L, "John", "Doe", "john@example.com", "password" , null);
        Employee updatedEmployee = new Employee(1L, "John", "Smith", "john@example.com", "newpassword" , null);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(updatedEmployee);

        assertEquals("Smith", result.getLastName());
        assertEquals("newpassword", result.getPassword());
    }

    @Test
    public void testDeleteEmployee_Success() {
        long employeeId = 1L;
        Employee employee = new Employee(employeeId, "John", "Doe", "john@example.com", "password", null);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).delete(employee);
    }
}
