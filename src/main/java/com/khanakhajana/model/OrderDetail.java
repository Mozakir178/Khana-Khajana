package com.khanakhajana.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import lombok.Data;

@Entity
@Data
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private LocalDateTime orderDate;
	@OneToOne
	@JoinColumn(name = "cartId")
	private FoodCart cart;
	private Boolean status;
	@ManyToOne
	private Restaurant restaurant;
	@ManyToOne
	private User customer;
	
//	@OneToOne
//	@JsonIgnore
//	private  Bill bill;
	
	

}
