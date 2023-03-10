package com.khanakhajana.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private String buildingNo;
	private String streetNo;
	private String area;
	private String city;
	private String state;
	private String country;
	private String pincode;
		
}
