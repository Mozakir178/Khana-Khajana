package com.khanakhajana.service;

import com.khanakhajana.exception.ItemException;
import com.khanakhajana.exception.RestaurantException;
import com.khanakhajana.model.Item;

import java.util.List;

public interface ItemService {

	public Item addItem(Item item, String key) throws ItemException, RestaurantException;

	public Item updateItem(Item item, String key) throws ItemException, RestaurantException;

	public Item viewItem(Integer id, String key) throws ItemException, RestaurantException;

	public Item removeItem(Integer id, String key) throws ItemException, RestaurantException;

	public List<Item> viewAllItemBycategory(Integer id, String userkey) throws ItemException, RestaurantException;
	
	public List<Item> viewAllItemByName(String name,String key) throws ItemException, RestaurantException;
	
	public List<Item> viewAllItemByRestaurant(Integer id,String key) throws ItemException, RestaurantException;

	
	
}
