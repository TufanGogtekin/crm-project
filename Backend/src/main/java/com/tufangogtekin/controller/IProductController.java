package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoProduct;

public interface IProductController {

	DtoProduct saveProduct(DtoProduct dtoProduct);
	
	List<DtoProduct> getAllProduct();
	
}
