package com.tufangogtekin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoCustomer;
import com.tufangogtekin.model.Customer;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.repository.CustomerRepository;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DtoCustomer saveCustomer(DtoCustomer dtoCustomer) {

		Customer customer = new Customer();

		BeanUtils.copyProperties(dtoCustomer, customer);

		if (dtoCustomer.getEmployeeId() != null) {
			Optional<Employee> optionalEmployee = employeeRepository.findById(dtoCustomer.getEmployeeId());
			if (optionalEmployee.isPresent()) {
				customer.setEmployee(optionalEmployee.get());
			}
		}
		
		Customer savedCustomer = customerRepository.save(customer);
		
		DtoCustomer reDtoCustomer = new DtoCustomer();
		
		BeanUtils.copyProperties(savedCustomer, reDtoCustomer);
		
		if (savedCustomer.getEmployee() != null) { 
	        reDtoCustomer.setEmployeeId(savedCustomer.getEmployee().getId());
	    }
		
		return reDtoCustomer;

	}

	@Override
	public List<DtoCustomer> getAllCustomers() {
		
		List<Customer> CustomerList = customerRepository.findAll();
		
		List<DtoCustomer> dtoCustomersList = new ArrayList<>();
		
		Employee emp = new Employee();
		
		for (Customer customer : CustomerList) {
			DtoCustomer dto = new DtoCustomer();
			BeanUtils.copyProperties(customer, dto);
			if(dto.getEmployeeId()==null) {
				dto.setEmployeeId(customer.getEmployee().getId());
				Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
				if(employee.isPresent()) {
					emp = employee.get();
					dto.setEmployeeName(emp.getName());
					dto.setEmployeeSurname(emp.getLastName());
				}
			}
			dtoCustomersList.add(dto);
 		}
		return dtoCustomersList;
	}

	@Override
	public List<DtoCustomer> getCustomersByName(String name) {
		List<Customer> customerList = customerRepository.findAll();
		
		List<DtoCustomer> dtoCustomers = new ArrayList<>();
		
		for (Customer customer : customerList) {
			
			if(customer.getName() != null && customer.getName().equalsIgnoreCase(name)) {
				DtoCustomer dto = new DtoCustomer();
				BeanUtils.copyProperties(customer, dto);
				if (customer.getEmployee() != null) {
					dto.setEmployeeId(customer.getEmployee().getId());
					dto.setEmployeeName(customer.getEmployee().getName());
					dto.setEmployeeSurname(customer.getEmployee().getLastName());
				}
				dtoCustomers.add(dto);
			}
			
		}
		
		return dtoCustomers;
		
	}

}
