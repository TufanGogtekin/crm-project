package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.IEmployeeController;
import com.tufangogtekin.dto.DtoEmployee;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.service.IEmployeeService;

@RestController
@RequestMapping("/rest/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeControllerImpl implements IEmployeeController{

	@Autowired
	IEmployeeService employeeService;
	
	@Override
	@PostMapping(path = "/save")
	public String saveEmployee(@RequestBody Employee employee) {	
		
		return employeeService.saveEmployee(employee);
	}

	@Override
	@GetMapping(path = "/find/id/{id}")
	public DtoEmployee getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@Override
	@GetMapping(path = "/list")
	public List<DtoEmployee> getAllEmployees() {	
		return employeeService.getAllEmployees();
	}

	@Override
	@GetMapping(path = "/find/name/{name}")
	public List<DtoEmployee> getEmployeeByName(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);
	}
	
	

}
