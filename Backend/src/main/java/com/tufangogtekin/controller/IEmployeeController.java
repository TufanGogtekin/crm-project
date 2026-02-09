package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoEmployee;
import com.tufangogtekin.model.Employee;

public interface IEmployeeController {

		String saveEmployee(Employee employee);
	    
	    DtoEmployee getEmployeeById(Long id);
	    
	    List<DtoEmployee> getAllEmployees();
	    
	    List<DtoEmployee> getEmployeeByName(String name);
	
}
