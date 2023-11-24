package com.springboot.ecomerceapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Customer;
import com.springboot.ecomerceapp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer postCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer getCustomerById(int cid) throws InvalidIdException {
		Optional<Customer> optional =  customerRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("customer id invalid");
		return optional.get();
	}

}
