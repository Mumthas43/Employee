package com.socgen.employee;

import com.socgen.employee.model.Employee;
import com.socgen.employee.repository.EmployeeRepository;
import com.socgen.employee.service.EmployeeService;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EmployeeApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:appication-test.properties")
public class EmployeeApplicationITTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void contextLoads() throws Exception{


        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employees")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println("Integration Test"+mvcResult.getResponse());

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
