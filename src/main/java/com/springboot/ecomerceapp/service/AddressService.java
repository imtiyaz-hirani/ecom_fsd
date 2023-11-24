package com.springboot.ecomerceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecomerceapp.model.Address;
import com.springboot.ecomerceapp.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address postAddress(Address address) {
		
		return addressRepository.save(address);
	}

}
