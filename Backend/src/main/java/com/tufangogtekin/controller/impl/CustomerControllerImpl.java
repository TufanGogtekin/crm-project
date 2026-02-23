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

import com.tufangogtekin.controller.ICustomerController;
import com.tufangogtekin.dto.DtoCustomer;
import com.tufangogtekin.service.ICustomerService;

@RestController
@RequestMapping("/rest/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerControllerImpl implements ICustomerController {

	@Autowired
	ICustomerService customerService;

	@Override
	@PostMapping(path = "/save")
	public DtoCustomer saveCustomer(@RequestBody DtoCustomer dtoCustomer) {	
		return customerService.saveCustomer(dtoCustomer);
	}

	@Override
	@GetMapping(path = "/list")
	public List<DtoCustomer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@Override
	@GetMapping(path = "/find/name/{name}")
	public List<DtoCustomer> getCustomersByName(@PathVariable("name") String name) {
		return customerService.getCustomersByName(name);
	}
	
}
