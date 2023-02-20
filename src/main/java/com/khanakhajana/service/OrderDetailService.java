package com.khanakhajana.service;

import com.khanakhajana.exception.OrderDetailException;
import com.khanakhajana.exception.RestaurantException;
import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

	public OrderDetail addDetails(String key);

	public String cancelOrderByRestaurant(Integer orderId, String key) throws RestaurantException;
	
	public String cancelOrderByCustomer(Integer orderId, String key);
	
	public OrderDetail acceptOrderByRestaurant(Integer orderId, String key) throws RestaurantException;
	
	public List<OrderDetail> viewAllOrderByRestaurant(String key) throws OrderDetailException,RestaurantException;
	
	public List<OrderDetail> viewAllOrderByCustomer(String key) throws OrderDetailException, UserException;

}
