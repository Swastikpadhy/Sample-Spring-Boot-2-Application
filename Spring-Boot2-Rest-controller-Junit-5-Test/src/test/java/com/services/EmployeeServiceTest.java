package com.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Employee;


@SpringBootTest
class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetAll() {
		assertEquals(4, employeeService.getAll().size());
	}

	

	@Test
	void testSave() {
		Employee empStub = new Employee(5, "Test 5", "Test 5");
		assertEquals(5, employeeService.save(empStub).getEmpId());
	}

}
