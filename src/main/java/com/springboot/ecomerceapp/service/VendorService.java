package com.springboot.ecomerceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Vendor;
import com.springboot.ecomerceapp.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	public Vendor postVendor(Vendor vendor) {
		// I vl givew this vendor obj to repository
		return vendorRepository.save(vendor);
	}

	public Vendor getOne(int id) throws InvalidIdException {
		Optional<Vendor> optional =  vendorRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Vendor ID Invalid");
		}
		return optional.get();
	}

	public List<Vendor> getAll(Pageable pageable) {
		return vendorRepository.findAll(pageable).getContent();
	}

	public void deleteVendor(Vendor vendor) {
		vendorRepository.delete(vendor);
		
	}

}
