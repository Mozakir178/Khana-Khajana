package com.khanakhajana.controller;

import com.khanakhajana.model.User;
import com.khanakhajana.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	UserServices uservice;
	
	
	@PostMapping("/user")
	public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user){
		
		User u = uservice.addUser(user);
		
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
		
	}

	@PutMapping("/user")
	public ResponseEntity<User> updateUserHandler(@Valid @RequestBody User user){

		User u = uservice.updateUser(user);

		return new ResponseEntity<User>(u,HttpStatus.CREATED);

	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<User> removeUserHandler(@Valid @PathVariable("userId") Integer userId){

		User u = uservice.removeUser(userId);

		return new ResponseEntity<User>(u,HttpStatus.CREATED);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> viewUserHandler(@Valid @PathVariable("userId") Integer userId){

		User u = uservice.viewUser(userId);

		return new ResponseEntity<User>(u,HttpStatus.CREATED);

	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> viewAllUserHandler(){

		return new ResponseEntity<List<User>>(uservice.viewAllUser(),HttpStatus.OK);

	}

}
