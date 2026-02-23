package com.tufangogtekin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoProduct;
import com.tufangogtekin.model.Product;
import com.tufangogtekin.repository.ProductRepository;
import com.tufangogtekin.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public DtoProduct saveProduct(DtoProduct dtoProduct) {

		Product product = new Product();

		BeanUtils.copyProperties(dtoProduct, product);

		productRepository.save(product);

		return dtoProduct;
	}

	@Override
	public List<DtoProduct> getAllProduct() {
		List<Product> productList = productRepository.findAll();

		List<DtoProduct> dtoProductList = new ArrayList<>();

		for (Product product : productList) {
			DtoProduct dto = new DtoProduct();
			BeanUtils.copyProperties(product, dto);
			dtoProductList.add(dto);
		}
		return dtoProductList;
	}
}
