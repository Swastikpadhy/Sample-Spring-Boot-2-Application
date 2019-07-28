package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	List<Employee> empList;
	Employee emp = null;
	
	public EmployeeServiceImpl() {
		empList = new ArrayList<>();
		generateList();
	}

	@Override
	public List<Employee> getAll() {
		
		return empList;
	}
	
	
	public void generateList() {
		empList.add(new Employee(1, "Test 1", "Test 1"));
		empList.add(new Employee(2, "Test 2", "Test 2"));
		empList.add(new Employee(3, "Test 3", "Test 3"));
	}

	@Override
	public Employee save(Employee employee) {
		empList.add(employee);
		return employee;
	}

}
