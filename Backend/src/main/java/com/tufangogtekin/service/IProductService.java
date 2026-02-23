package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoProduct;

public interface IProductService {
	
	 DtoProduct saveProduct(DtoProduct dtoProduct);
	
	 List<DtoProduct> getAllProduct();

}
