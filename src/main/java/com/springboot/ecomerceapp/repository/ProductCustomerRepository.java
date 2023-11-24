package com.springboot.ecomerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecomerceapp.model.ProductCustomer;

public interface ProductCustomerRepository extends JpaRepository<ProductCustomer, Integer>{

}
