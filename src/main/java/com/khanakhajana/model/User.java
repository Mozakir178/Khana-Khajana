package com.khanakhajana.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String firstName;
	private String lastName;
	private Integer age;
	private String gender;
	@Column(unique = true)
	private String mobile;
	private String email;
	private UserType type;
	private String password;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AID")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	@JsonIgnore
	private FoodCart cart;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderDetail> orderDetails = new ArrayList<>();
	

	
}
