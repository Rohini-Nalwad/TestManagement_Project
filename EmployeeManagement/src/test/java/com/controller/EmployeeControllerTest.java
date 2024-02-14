package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.entity.Employee;
import com.exception.EmployeeNotFoundException;
import com.service.EmployeeService;
import com.service.TestService;

public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@Mock
	private TestService testService;

	@InjectMocks
	private EmployeeController employeeController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegisterEmployee_Success() {
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setEmail("john@example.com");
		employee.setPassword("password");

		when(employeeService.register(employee)).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.register(employee);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(employee, responseEntity.getBody());
	}

	@Test
	public void testGetAllEmployees_Success() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1L, "John", "Doe", "john@example.com", "password", null));
		employees.add(new Employee(2L, "Jane", "Doe", "jane@example.com", "password", null));

		when(employeeService.getAllEmployees()).thenReturn(employees);

		List<Employee> retrievedEmployees = employeeController.getAllEmployees();

		assertEquals(2, retrievedEmployees.size());
		assertEquals("John", retrievedEmployees.get(0).getFirstName());
		assertEquals("Jane", retrievedEmployees.get(1).getFirstName());
	}

	@Test
	public void testGetEmployeeById_Success() {
		long employeeId = 1L;
		Employee employee = new Employee(employeeId, "John", "Doe", "john@example.com", "password", null);

		when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);
		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(employeeId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(employee, responseEntity.getBody());
	}

	@Test
	public void testGetEmployeeById_NotFound() {
	
		long employeeId = 1L;

		when(employeeService.getEmployeeById(employeeId))
				.thenThrow(new EmployeeNotFoundException("Employee not found"));

		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeController.getEmployeeById(employeeId);
		});
	}

	@Test
	public void testUpdateEmployee_Success() {
		// Given
		Employee employee = new Employee(1L, "John", "Doe", "john@example.com", "password", null);

		when(employeeService.updateEmployee(employee)).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(employee);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(employee, responseEntity.getBody());
	}

	@Test
	public void testDeleteEmployee_Success() {
		long employeeId = 1L;

		ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(employeeId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
