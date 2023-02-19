package com.khanakhajana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
	
	private int userId;
	private String userName;
	private String password;

}
