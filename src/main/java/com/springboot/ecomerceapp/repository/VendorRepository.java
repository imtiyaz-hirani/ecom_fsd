package com.springboot.ecomerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecomerceapp.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{

}
