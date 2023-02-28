package com.khanakhajana.controller;

import com.khanakhajana.model.Restaurant;
import com.khanakhajana.service.RestaurentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {
	@Autowired
	private RestaurentService rs;

	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
		return new ResponseEntity<Restaurant>(rs.addRestaurant(restaurant), HttpStatus.CREATED);
	}

	@PutMapping("/restaurant/{key}")
	public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable("key") String key) {
		return rs.updateRestaurant(restaurant, key);
	}

	@PostMapping("/restaurants/{key}")
	public Restaurant deleteRestaurant(@RequestBody Restaurant restaurant, @PathVariable("key") String key) {
		return rs.removeRestaurant(restaurant, key);
	}

	@GetMapping("/restaurant/{restaurant}/{key}")
	public Restaurant viewRestaurant(@PathVariable("restaurant") Integer restaurant, @PathVariable("key") String key) {
		return rs.viewRestaurant(restaurant, key);
	}

	@GetMapping("/restaurantsbylocation/{location}/{key}")
	public ResponseEntity<List<Restaurant>> viewRestaurantByLocation(@PathVariable("location") String location,
			@PathVariable("key") String key) {
		return new ResponseEntity<List<Restaurant>>(rs.viewRestaurantByLocation(location, key), HttpStatus.OK);

	}

	@GetMapping("/restaurantsbyname/{name}/{key}")
	public ResponseEntity<List<Restaurant>> viewRestaurantByName(@PathVariable("name") String name,
			@PathVariable("key") String key) {

		return new ResponseEntity<List<Restaurant>>(rs.viewRestaurantsByItemName(name, key), HttpStatus.OK);
	}

}
