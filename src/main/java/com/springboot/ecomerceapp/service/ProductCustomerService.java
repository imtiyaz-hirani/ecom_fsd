package com.springboot.ecomerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecomerceapp.model.ProductCustomer;
import com.springboot.ecomerceapp.repository.ProductCustomerRepository;

@Service
public class ProductCustomerService {

	@Autowired
	private ProductCustomerRepository productCustomerRepository;
	
	public ProductCustomer post(ProductCustomer productCustomer) {
		
		return productCustomerRepository.save(productCustomer);
	}

}
