package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoProduct;

public interface IProductService {
	
	public DtoProduct saveProduct(DtoProduct dtoProduct);
	
	public List<DtoProduct> getAllProduct();

}
