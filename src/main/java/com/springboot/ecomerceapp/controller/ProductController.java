package com.springboot.ecomerceapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Product;
import com.springboot.ecomerceapp.model.Vendor;
import com.springboot.ecomerceapp.service.ProductService;
import com.springboot.ecomerceapp.service.VendorService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add/{vid}")
	public ResponseEntity<?> postProduct(@RequestBody Product product, 
						    @PathVariable("vid") int vid) {
			/* Fetch vendor object from db using vid */
		try {
			Vendor vendor = vendorService.getOne(vid);
			/* Attach vendor to product */
			product.setVendor(vendor);
			/* Save the product in the DB */
			product = productService.postProduct(product);
			return ResponseEntity.ok().body(product);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/all")
	public List<Product> getAllProducts(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return productService.getAllProducts(pageable);
	}
	
	@GetMapping("/all/{vid}")
	public ResponseEntity<?> getProductsByVendor(@PathVariable("vid") int vid) {
		/* Fetch vendor object using given vid */
		try {
			Vendor vendor = vendorService.getOne(vid);
			List<Product> list= productService.getProductsByVendor(vid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
}

/* 
 *  GET: 
 *  POST:
 *  PUT:
 *  DELETE: 
 * */
 