package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoCustomer;

public interface ICustomerController {

	public DtoCustomer saveCustomer(DtoCustomer dtoCustomer);
	
	List<DtoCustomer> getAllCustomers();
	
	List<DtoCustomer> getCustomersByName(String name);
}

