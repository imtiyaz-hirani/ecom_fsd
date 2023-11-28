package com.springboot.ecomerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomerceapp.dto.VendorDto;
import com.springboot.ecomerceapp.enums.RoleType;
import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.User;
import com.springboot.ecomerceapp.model.Vendor;
import com.springboot.ecomerceapp.service.UserService;
import com.springboot.ecomerceapp.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService; // --DI

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	/* 
	 * API: Adding vendor with User details
	 * Method: POST
	 * Path: /vendor/add
	 * Param: 
	 * RequestBody: 
	 * {
	    "name":"ABC Electronics",
	    "city":"mumbai",
	    "user":{
	        "username":"abc@gmail.com",
	        "password":"abc@123"
    		}
		}
	 * Response: Vendor
	 * */
	@PostMapping("/add") // api: /vendor/post --DI
	public Vendor postVendor(@RequestBody Vendor vendor) { // ur method is mapped to a URL : api
		/* Fetch user details from vendor and save it in DB*/
		User user = vendor.getUser();
		//attach role to user 
		user.setRole(RoleType.VENDOR);
		//encoding the password
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		/* this user that comes from the DB gives us ID as well */
		user = userService.postUser(user);
		/* Attach user to vendor and save vendor */
		vendor.setUser(user);
		vendor = vendorService.postVendor(vendor);
		return vendor;
	}

	/*
	 * Read id and fetch the object from the DB
	 * 
	 * @param id - vendor id
	 */
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Vendor vendor = vendorService.getOne(id);
			return ResponseEntity.ok().body(vendor);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	// localhost:8082/vendor/getall?page=2&size=2

	@GetMapping("/getall") /// vendor/getall?page=0&size=10
	public List<Vendor> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																										// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return vendorService.getAll(pageable);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVendor(@PathVariable("id") int id) {
		
		try {
			//validate id
			Vendor vendor = vendorService.getOne(id);
			//delete
			vendorService.deleteVendor(vendor);
			return ResponseEntity.ok().body("vendor deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateVendor(@PathVariable("id") int id,
							@RequestBody VendorDto newVendor) {
		try {
			//validate id
			Vendor oldVendor = vendorService.getOne(id);
			if(newVendor.getCity() != null)
				oldVendor.setCity(newVendor.getCity());
			if(newVendor.getName() != null) 
				oldVendor.setName(newVendor.getName()); 
			 
			oldVendor = vendorService.postVendor(oldVendor); 
			return ResponseEntity.ok().body(oldVendor);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
/*
 * GET: getAll getOne(id) : @GetMapping POST: @PostMapping PUT: @PutMapping
 * DELETE: @DeleteMapping
 */