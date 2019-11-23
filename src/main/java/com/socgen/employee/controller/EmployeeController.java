package com.socgen.employee.controller;

import com.socgen.employee.model.Employee;
import com.socgen.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees")
    public ResponseEntity getEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();

        return new ResponseEntity(employeeList,HttpStatus.OK);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity("Employee details saved Successfully",HttpStatus.CREATED);

    }
}
