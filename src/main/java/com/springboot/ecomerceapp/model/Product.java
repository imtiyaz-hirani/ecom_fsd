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
import javax.persistence.OneToOne;

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
	
	private boolean featured; //findByFeatured(true)
	
	private String image; 
	
	@ManyToOne
	private Vendor vendor; //findByVendorId(vid) : List<Product> : findByVendorName(vname)
	
	@OneToOne
	private Category category;  //findByCategoryId : List<Product> 
	
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	} 
	
	
	
}
