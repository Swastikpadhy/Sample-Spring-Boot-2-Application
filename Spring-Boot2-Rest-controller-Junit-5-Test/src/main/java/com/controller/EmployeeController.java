package com.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
    EmployeeService empService;
	
	 @RequestMapping(method = RequestMethod.GET)
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	        List<Employee> employees = empService.getAll();
	        if (employees.isEmpty()) {
	            logger.info("Employees does not exists");
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        logger.info("Found " + employees.size() + " Employees");
	        logger.info(Arrays.toString(employees.toArray()));
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }
	 
	 @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
	        empService.save(employee);
	        logger.info("Added:: " + employee);
	        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	    }

}
