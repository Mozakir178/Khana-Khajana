package com.khanakhajana.repository;

import com.khanakhajana.exception.RestaurantException;
import com.khanakhajana.exception.UserLoginException;
import com.khanakhajana.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer>{

	public Restaurant findByMobile(String mobile) throws UserLoginException;
	@Query("select c from Restaurant c where c.address.city=?1")
	public List<Restaurant> findByAddress(String address) throws RestaurantException;
	public List<Restaurant> findByItems(String name) throws RestaurantException;

}
