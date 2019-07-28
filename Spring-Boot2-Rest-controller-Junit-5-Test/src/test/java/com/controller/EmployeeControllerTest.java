package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.model.Employee;
import com.services.EmployeeService;
import com.util.TestUtils;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService empService;
    
    List<Employee> empList;

    private final String URL = "/employee/";
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetAllEmployees() throws Exception {
		// prepare data and mock's behaviour
        List<Employee> empList = buildEmployees();
        when (empService.getAll()).thenReturn(empList);
        
        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals (HttpStatus.OK.value(), status, "Incorrect Response Status");
        
     // verify that service method was called once
        verify (empService).getAll();
        assertEquals (3,empService.getAll().size());
        
   }
	
	@Test
	void testGetAllEmployeesEmptyList() throws Exception {
		// prepare data and mock's behaviour
        List<Employee> empList = buildEmptyEmployees();
        when (empService.getAll()).thenReturn(empList);
        
        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals (HttpStatus.NO_CONTENT.value(), status, "Incorrect Response Status");
        
     // verify that service method was called once
        verify (empService).getAll();
        assertEquals (0,empService.getAll().size());
        
   }

	@Test
    public void testAddEmployee() throws Exception {
		 // prepare data and mock's behaviour
        Employee empStub = new Employee(5, "Test 5", "Test 5");
        when(empService.save(any(Employee.class))).thenReturn(empStub);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(empStub))).andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status, "Incorrect Response Status");

        // verify that service method was called once
        verify(empService).save(any(Employee.class));

        Employee resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Employee.class);
        assertNotNull(resultEmployee);
        assertEquals(5, resultEmployee.getEmpId());
	}
	
	private List<Employee> buildEmployees() {
		empList = new ArrayList<>();
		empList.add(new Employee(1, "Test 1", "Test 1"));
		empList.add(new Employee(2, "Test 2", "Test 2"));
		empList.add(new Employee(3, "Test 3", "Test 3"));
        return empList;
    }
	
	private List<Employee> buildEmptyEmployees() {		
        return  new ArrayList<>();
    }
}
