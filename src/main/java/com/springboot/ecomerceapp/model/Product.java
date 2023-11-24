package com.springboot.ecomerceapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Product { //p

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;  //findById(id)
	
	@Column(nullable = false)
	private String title; //findByTitle(title)
	
	private double price; //findByPrice(price): List<Product>
	
	@Column(length = 2000)
	private String description; 
	
	@ManyToOne
	private Vendor vendor; //findByVendorId(vid) : List<Product> : findByVendorName(vname)
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	} 
	
	
	
}
