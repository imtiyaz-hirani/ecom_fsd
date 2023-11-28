package com.springboot.ecomerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecomerceapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
