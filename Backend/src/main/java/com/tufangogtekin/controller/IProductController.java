package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoProduct;

public interface IProductController {

	public DtoProduct saveProduct(DtoProduct dtoProduct);
	
	public List<DtoProduct> getAllProduct();
	
}
