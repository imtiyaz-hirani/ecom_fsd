package com.springboot.ecomerceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.ecomerceapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select * from product where vendor_id=?1",nativeQuery = true)
	List<Product> getProductsByVendorNative(int vid);

	@Query("select p from Product p where p.vendor.id=?1")
	List<Product> getProductsByVendorJpql(int vid);

	List<Product> findByVendorId(int vid);

}
/* 
 * JPQL: we write jpql on model classes : DB Independent 
 * Native: we write Native queries on DB tables  
 * 
 * JPQL: HIbernate uses joins and JPQL is faster executed.
 * Native: This is executed by DB Parser.  
 * 
 * Native: select * from product p where p.vendor_id=?1
 * JPQL: select p from Product p where p.vendor.id=?1
 * */
 