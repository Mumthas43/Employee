package com.socgen.employee;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.socgen.employee.model.Employee;
import com.socgen.employee.repository.EmployeeRepository;
import com.socgen.employee.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeRepository employeeRepository;

	@Test
	public void contextLoads() throws Exception{

		when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/employees")
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk())
				.andReturn();

		System.out.println("Unit Test "+mvcResult.getResponse());
		verify(employeeRepository).findAll();

		String json = "{\n" +
				" \"firstName\" : \"Suresh\",\n" +
				" \"lastName\" : \"Sharma\",\n" +
				" \"gender\" : \"Male\",\n" +
				" \"dob\" : \"1996-01-14\",\n" +
				" \"department\" : \"DevOps\"\n" +
				"}";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/employees")
						.contentType(MediaType.APPLICATION_JSON).content(json)
		.characterEncoding("utf-8"))
				.andExpect(status().isCreated())
				.andReturn();
	}
}
