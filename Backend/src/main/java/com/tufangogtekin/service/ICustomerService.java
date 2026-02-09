package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoCustomer;

public interface ICustomerService {

	DtoCustomer saveCustomer(DtoCustomer dtoCustomer);
	
	List<DtoCustomer> getAllCustomers();
	
	List<DtoCustomer> getCustomersByName(String name);
	
}
