package com.socgen.employee.service;

import com.socgen.employee.model.Employee;
import com.socgen.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee){

        employeeRepository.save(employee);
    }

}
