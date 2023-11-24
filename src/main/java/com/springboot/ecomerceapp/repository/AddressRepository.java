package com.springboot.ecomerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.springboot.ecomerceapp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
