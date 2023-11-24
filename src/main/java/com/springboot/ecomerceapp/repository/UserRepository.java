package com.springboot.ecomerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecomerceapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
