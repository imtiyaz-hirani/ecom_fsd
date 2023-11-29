package com.springboot.ecomerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomerceapp.model.Category;
import com.springboot.ecomerceapp.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public Category postCategory(@RequestBody Category category) {
		return categoryService.postCategory(category);
	}
	
	@GetMapping("/all")
	public List<Category> getAll(){
		return categoryService.getAll();
	}
}
