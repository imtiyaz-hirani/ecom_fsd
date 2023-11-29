package com.springboot.ecomerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Product;
import com.springboot.ecomerceapp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product postProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable).getContent() ;
	}

	public List<Product> getProductsByVendor(int vid) {
		/* 
		 * 1. Native Query : productRepository.getProductsByVendorNative(vid)
		 * 2. JPQL : productRepository.getProductsByVendorJpql(vid);
		 * 3. JPA Instance methods : productRepository.findByVendorId(vid)
		 * */
		return productRepository.findByVendorId(vid);
	}

	public Product getProductById(int pid) throws InvalidIdException {
		Optional<Product> optional =  productRepository.findById(pid);
		if(!optional.isPresent())
			throw new InvalidIdException("Product ID Invalid");
		return optional.get();
	}

	public List<Product> getProductsByCategoryId(int cid, Pageable pageable) {
		 
		return productRepository.getProductsByCategoryId(cid,pageable);
	}

	public List<Product> getFeaturedProducts() {
		 
		return productRepository.getFeaturedProducts(true);
	}

	public List<Product> searchProductByName(String qStr) {
		 
		return productRepository.searchProductByName(qStr);
	}

}
