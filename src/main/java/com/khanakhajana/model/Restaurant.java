package com.khanakhajana.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantaid;
	private String restaurantName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Aid")
	private Address address;
	@OneToMany(mappedBy = "restaurant" ,cascade = CascadeType.DETACH)
	@JsonIgnore
	private List<Item> items;
	private String ManagerName;
	private String mobile;
	private String password;
	private UserType type;
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.DETACH)
	@JsonIgnore
	private List<OrderDetail> orderDetails = new ArrayList<>();

	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn(name = "resId")
	@JsonIgnore
	private List<Category> categories = new ArrayList<>();
}
