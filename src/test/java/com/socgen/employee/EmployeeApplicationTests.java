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
	MockMvc mockMvc1;

	@Autowired
	MockMvc mockMvc2;

	@MockBean
	EmployeeRepository employeeRepository;

	@Test
	public void contextLoads() throws Exception{

		when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc1.perform(
				MockMvcRequestBuilders.get("/employees")
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk())
				.andReturn();

		System.out.println("Unit Test "+mvcResult.getResponse());
		verify(employeeRepository).findAll();

		Employee e1 = new Employee(Long.valueOf(101),"Ramesh","Patil","Male",new Date(03-01-1992),"Support");

		//when(employeeRepository.save(e1)).thenReturn();
		String json = "{\n" +
				" \"id\" : \"105\",\n " +
				" \"firstName\" : \"Suresh\",\n" +
				" \"lastName\" : \"Sharma\",\n" +
				" \"gender\" : \"Male\",\n" +
				" \"dob\" : \"1996-01-14T00:00:00.000+0000\",\n" +
				" \"department\" : \"DevOps\"\n" +
				"}";
		mockMvc2.perform(
				MockMvcRequestBuilders.post("/employees")
						.contentType(MediaType.APPLICATION_JSON).content(json)
		.characterEncoding("utf-8"))
				.andExpect(status().isCreated())
				/*.andExpect(jsonPath("$.firstName",Matchers.is("Suresh")))
				.andExpect(jsonPath("$.lastName",Matchers.is("Sharma")))
				.andExpect(jsonPath("$.gender",Matchers.is("Male")))
				.andExpect(jsonPath("$.department",Matchers.is("DevOps")))*/
				.andReturn();

		//System.out.println("Unit Test save: "+mvcResult1.getResponse());
		//verify(employeeRepository).save(e1);

		mockMvc1.perform(
				MockMvcRequestBuilders.get("/employees")
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk())
				.andExpect(jsonPath("$",Matchers.hasSize(0)));

		mockMvc1.perform(
				MockMvcRequestBuilders.get("/")
		).andExpect(status().isOk())
				.andExpect(content().string("Hii"));
		//If you have entereda value and suppose json has {titile,value} and is not a list
		//Below code tets for the contents
		/*mockMvc1.perform(
				MockMvcRequestBuilders.get("/employees")
						.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk())
				.andExpect(jsonPath("$.title",Matchers.is("Greetings")))
				.andExpect(jsonPath("$.value",Matchers.is("Hello World")))
				.andExpect(jsonPath("$.*",Matchers.hasSize(2)));*/
	}



	/*@Autowired
	MockMvc mockMvc;

	@Autowired
	private EmployeeService employeeService;

	@Test
	void getAllEmployees() throws Exception{
		Map<Long,Employee> employeeList = new HashMap<>();
		employeeList.put(Long.valueOf(101),new Employee(Long.valueOf(101),"Ramesh","Patil","Male",new Date(03-01-1992),"Support"));
		when(employeeService.getListOfEmployees()).thenReturn(employeeList);

		mockMvc.perform(MockMvcRequestBuilders.get("/employees").contentType(MediaType.APPLICATION_JSON));
	}*/

}
