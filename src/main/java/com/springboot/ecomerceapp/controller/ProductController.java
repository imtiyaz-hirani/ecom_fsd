package com.springboot.ecomerceapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Category;
import com.springboot.ecomerceapp.model.Product;
import com.springboot.ecomerceapp.model.Vendor;
import com.springboot.ecomerceapp.service.CategoryService;
import com.springboot.ecomerceapp.service.ProductService;
import com.springboot.ecomerceapp.service.VendorService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProductController {

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add/{vid}/{cid}")
	public ResponseEntity<?> postProduct(@RequestBody Product product, 
						    @PathVariable("vid") int vid,
						    @PathVariable("cid") int cid) {
			/* Fetch vendor object from db using vid */
		try {
			Vendor vendor = vendorService.getOne(vid);
			/* Attach vendor to product */
			product.setVendor(vendor);
			
			/* Fetch category object from db using cid */
			Category category = categoryService.getById(cid);
			
			/* Attach category to vendor */
			product.setCategory(category);
			
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
	
	
	/* Display list of products by category id. take category id as path variable,add pagination */
	@GetMapping("/category/all/{cid}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable("cid") int cid ,
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		/* validate category id. */
		try {
			Category category= categoryService.getById(cid);
			/* fetch products by category id with pagination */
			Pageable pageable=PageRequest.of(page, size);
			List<Product> list = productService.getProductsByCategoryId(cid,pageable);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	 
	/* 
	 * API: Display list of products that are featured.
	 * Method: GET
	 * Path: /product/featured
	 * Param: 
	 * Response: List<Product>
	 * */
	@GetMapping("/featured")
	public List<Product> getFeaturedProducts() {
		return productService.getFeaturedProducts();
	}
	
	@GetMapping("/search/{qStr}")
	public List<Product> searchProductByName(@PathVariable("qStr") String qStr) {
		List<Product> list= productService.searchProductByName(qStr);
		return list; 
	}
}

/* 
 *  GET: 
 *  POST:
 *  PUT:
 *  DELETE: 
 *  
 *  ctrl + shft + O : for auto import
 * */
 