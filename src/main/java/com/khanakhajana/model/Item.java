package com.khanakhajana.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Item {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer itemId;
	private String itemName;
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	private Integer cost;
	@ManyToOne(fetch = FetchType.EAGER)
	private Restaurant restaurant;
	private Integer Quantity;
	
	

	
	
}
