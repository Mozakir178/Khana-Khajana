package com.khanakhajana.controller;

import com.khanakhajana.model.FoodCart;
import com.khanakhajana.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodCartController {

	@Autowired
	private CartService fco;

	@PostMapping("/addtocart/{id}/{key}")
	public ResponseEntity<FoodCart> addItemToCartHandler(@PathVariable("id") Integer id,
			@PathVariable("key") String key) {

		FoodCart cart = fco.addItemToCart(id, key);

		return new ResponseEntity<FoodCart>(cart, HttpStatus.CREATED);

	}
//	
	
	@PutMapping("/reduceQuantity/{id}/{key}/{quantity}")
	public ResponseEntity<FoodCart> reduceQuantityHandler(@PathVariable("id") Integer id,
			@PathVariable("key") String key,@PathVariable("quantity") Integer q) {

		FoodCart cart = fco.decreaseQuantityInCart(id, q, key);

		return new ResponseEntity<FoodCart>(cart, HttpStatus.CREATED);

	}
	
	@PutMapping("/increaseQuantity/{id}/{key}/{quantity}")
	public ResponseEntity<FoodCart> increaseQuantityHandler(@PathVariable("id") Integer id,
			@PathVariable("key") String key,@PathVariable("quantity") Integer q) {

		FoodCart cart = fco.increaseQuantityInCart(id, q, key);

		return new ResponseEntity<FoodCart>(cart, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/removefromcart/{id}/{key}")
	public ResponseEntity<FoodCart> removeItemHandler(@PathVariable("id") Integer id, @PathVariable("key") String key) {

		FoodCart cart = fco.removeItemFromCart(id, key);

		return new ResponseEntity<FoodCart>(cart, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/clearcart/{key}")
	public ResponseEntity<String> clearCartHandler(@PathVariable("key") String key) {

		String message = fco.removeAllItemFromCart(key);

		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/viewcart/{key}")
	public ResponseEntity<FoodCart> viewcartHandler(@PathVariable("key") String key) {

	

		return new ResponseEntity<FoodCart>(fco.viewCart(key), HttpStatus.ACCEPTED);

	}

}
