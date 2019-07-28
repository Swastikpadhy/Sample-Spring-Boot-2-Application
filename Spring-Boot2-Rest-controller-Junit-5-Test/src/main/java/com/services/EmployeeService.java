package com.services;

import java.util.List;

import com.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAll();
	Employee save(Employee employee);
	

}
