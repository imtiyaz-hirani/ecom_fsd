package com.springboot.ecomerceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.ecomerceapp.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService; 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 //tell spring to go to user table in the db and read username/password
		auth.authenticationProvider(getProvider());
	}
	
	private AuthenticationProvider getProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		//also, I want spring to know that I have encrypted password in the db
		dao.setPasswordEncoder(getEncoder());
		//from here.. i want spring to go to my database and fetch users. 
		dao.setUserDetailsService(userService);  //UserDetailsService : UserService
		return dao; 
		 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .antMatchers(HttpMethod.POST,"/auth/login").authenticated()
		 .anyRequest().permitAll()
		 .and().httpBasic()
		 .and().cors().disable()
		 .csrf().disable();
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
