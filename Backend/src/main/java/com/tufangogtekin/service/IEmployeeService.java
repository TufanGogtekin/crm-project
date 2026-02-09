package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoEmployee;
import com.tufangogtekin.model.Employee;

public interface IEmployeeService {

    String saveEmployee(Employee employee);
    
    DtoEmployee getEmployeeById(Long id);
    
    List<DtoEmployee> getAllEmployees();
    
    List<DtoEmployee> getEmployeeByName(String name);
	
}
