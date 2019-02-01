package com.booking.bookingmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="package")
public class Packages  implements Serializable  {
	
	private static final long serialVersionUID = 74458L; 
	
	@Id
	@GeneratedValue
	@Column(name="package_id")
	private Long id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="price")
	private float price;

	
	@Lob
	@Column(name="image")
	private byte[]  image;

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public byte[]  getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImage(byte[]  image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}
