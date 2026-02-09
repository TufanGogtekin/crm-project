package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.IProductController;
import com.tufangogtekin.dto.DtoProduct;
import com.tufangogtekin.service.IProductService;

@RestController
@RequestMapping("/rest/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductControllerImpl implements IProductController {

	@Autowired
	private IProductService productService;
	
	@Override
	@PostMapping(path = "/save")
	public DtoProduct saveProduct(@RequestBody DtoProduct dtoProduct) {
	
		return productService.saveProduct(dtoProduct);
	}

	@Override
	@GetMapping(path = "/list")
	public List<DtoProduct> getAllProduct() {
		
		return productService.getAllProduct();
	}

	

}
