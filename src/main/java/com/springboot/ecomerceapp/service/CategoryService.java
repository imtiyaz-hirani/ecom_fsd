package com.springboot.ecomerceapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecomerceapp.exception.InvalidIdException;
import com.springboot.ecomerceapp.model.Category;
import com.springboot.ecomerceapp.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category postCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getById(int cid) throws InvalidIdException {
		Optional<Category> optional = categoryRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("Category Id Invalid");
		return optional.get();
	}

	
}
