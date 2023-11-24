package com.springboot.ecomerceapp.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Customer;
import com.springboot.ecomerceapp.model.Product;
import com.springboot.ecomerceapp.model.ProductCustomer;
import com.springboot.ecomerceapp.service.CustomerService;
import com.springboot.ecomerceapp.service.ProductCustomerService;
import com.springboot.ecomerceapp.service.ProductService;

@RestController
public class ProductCustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCustomerService productCustomerService;

	@PostMapping("/product/customer/{pid}/{cid}")
	public ResponseEntity<?> purchaseApi(@PathVariable("pid") int pid, @PathVariable("cid") int cid,
			@RequestBody ProductCustomer productCustomer) {

		try {
			/* Fetch customer using given cid */
			Customer customer = customerService.getCustomerById(cid);
			
			/* Fetch product using given pid */
			Product product = productService.getProductById(pid);
			
			/* attach both to productcustomer obj */
			productCustomer.setCustomer(customer);
			productCustomer.setProduct(product);
			productCustomer.setPurchaseDate(LocalDate.now());
			productCustomer.setPurchaseTime(LocalTime.now());
			
			/* Save productCustomer obj in DB */
			productCustomer = productCustomerService.post(productCustomer);
			return ResponseEntity.ok().body(productCustomer);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
