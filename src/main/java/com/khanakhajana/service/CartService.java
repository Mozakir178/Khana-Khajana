package com.khanakhajana.service;

import com.khanakhajana.model.FoodCart;

public interface CartService {

	public FoodCart addItemToCart(Integer itemId, String key);

	public FoodCart increaseQuantityInCart(Integer itemId, Integer quantity, String key);

	public FoodCart decreaseQuantityInCart(Integer itemId, Integer quantity, String key);

	public FoodCart removeItemFromCart(Integer itemId, String key);

	public String removeAllItemFromCart(String key);
	
	public FoodCart viewCart(String key);


}
