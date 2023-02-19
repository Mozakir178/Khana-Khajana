package com.khanakhajana.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private LocalDateTime billDate;

	private Integer totalitems;

	private Double totalCost;
	
	@OneToOne(fetch = FetchType.EAGER)
	private OrderDetail orderDetails ;

	@ManyToOne
	@JoinColumn(name = "cusId")
	private User customer;
	
	

}
