package com.tufangogtekin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoEmployee;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		
		return "kayıt işlemi tamam";
	}

	@Override
	public DtoEmployee getEmployeeById(Long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);

	    if (employeeOptional.isPresent()) {
	        Employee employee = employeeOptional.get();

	        DtoEmployee dtoEmployee = new DtoEmployee();
	        BeanUtils.copyProperties(employee, dtoEmployee);

	        return dtoEmployee;
	    }
	    
	    return null;
	}

	@Override
	public List<DtoEmployee> getAllEmployees() {
	    List<Employee> employeeList = employeeRepository.findAll();
	    
	    List<DtoEmployee> dtoEmployeeList = new ArrayList<>();
	    
	    for (Employee employee : employeeList) {
	        DtoEmployee dto = new DtoEmployee();
	        BeanUtils.copyProperties(employee, dto);
	        dtoEmployeeList.add(dto);
	    }
	 
	    return dtoEmployeeList;
	}

	@Override
	public List<DtoEmployee> getEmployeeByName(String name) {
		List<Employee> employeeList = employeeRepository.findAll();
		
		List<DtoEmployee> dtoEmployeesList = new ArrayList<>();
		
		for (Employee employee : employeeList) {
			if(employee.getName() != null && employee.getName().equalsIgnoreCase(name)) {
				DtoEmployee dto = new DtoEmployee();
				BeanUtils.copyProperties(employee, dto);
				dtoEmployeesList.add(dto);
			}
		}
		return dtoEmployeesList;
	}

}
