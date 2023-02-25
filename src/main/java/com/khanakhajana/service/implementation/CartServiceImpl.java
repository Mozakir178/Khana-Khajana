package com.khanakhajana.service.implementation;

import com.khanakhajana.exception.FoodCartException;
import com.khanakhajana.exception.ItemException;
import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.*;
import com.khanakhajana.repository.FoodCartDao;
import com.khanakhajana.repository.ItemDao;
import com.khanakhajana.repository.SessionDao;
import com.khanakhajana.repository.UserDao;
import com.khanakhajana.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private FoodCartDao fdo;

	@Autowired
	private ItemDao ido;

	@Autowired
	private SessionDao sdo;

	@Autowired
	private UserDao udo;

	@Override
	public FoodCart addItemToCart(Integer itemId, String key) {

		Optional<Item> item = ido.findById(itemId);

		if (item.isPresent()) {

			CurrentUserSession cus = sdo.findByUuid(key);
			
			if(cus==null) {
				throw new UserException("enter valid key");
			}

			UserType uType = cus.getType();

			if (uType.name() == "Customer") {


				User user = udo.findById(cus.getUserId()).orElseThrow(() -> new UserException("No user found"));

				FoodCart cart2 = user.getCart();
				
				FoodCart cart = new FoodCart();
				
				if(cart2==null) {
					cart2 = cart;
				}
		
				cart2.getItemList().add(item.get());
				
				user.setCart(cart2);
				
				

				udo.save(user);

			return	fdo.save(cart2);

			} else {
				throw new FoodCartException("login as a customer");
			}

		} else {
			throw new ItemException("no item available with id :" + itemId);
		}

	}

	@Override
	public FoodCart increaseQuantityInCart(Integer itemId, Integer quantity, String key) {
		Optional<Item> item = ido.findById(itemId);

		if (item.isPresent()) {

			CurrentUserSession cus = sdo.findByUuid(key);

			if(cus==null) {
				throw new UserException("enter valid key");
			}

			UserType uType = cus.getType();

			if (uType.name() == "Customer") {

				User user = udo.findById(cus.getUserId()).orElseThrow(() -> new UserException("No user found"));

				FoodCart cart = user.getCart();

				List<Item> items = cart.getItemList();

				Item item2 = null;

				for(int i=0;i<items.size();i++) {
					if(items.get(i).getItemId()==itemId) {
						Item item3 = items.get(i);
						item3.setQuantity(item3.getQuantity()+quantity);
						ido.save(item3);
					}
				}

				cart.setItemList(items);
				fdo.save(cart);

				udo.save(user);

				return cart;

			} else {
				throw new FoodCartException("login as a customer");
			}

		} else {
			throw new ItemException("no item available with id :" + itemId);
		}

	}

	@Override
	public FoodCart decreaseQuantityInCart(Integer itemId, Integer quantity, String key) {
		Optional<Item> itemO = ido.findById(itemId);

		if (itemO.isPresent()) {

			Item item = itemO.get();

			CurrentUserSession cus = sdo.findByUuid(key);

			if(cus==null) {
				throw new UserException("enter valid key");
			}

			UserType uType = cus.getType();

			if (uType.name() == "Customer") {

				User user = udo.findById(cus.getUserId()).orElseThrow(() -> new UserException("No user found"));

				FoodCart cart = user.getCart();

				if(cart==null) {
					throw new FoodCartException("cart is empty");
				}

				List<Item> items = cart.getItemList();

				for(int i=0;i<items.size();i++) {
					if(items.get(i).getItemId()==itemId) {
						Item item3 = items.get(i);
						item3.setQuantity(item3.getQuantity()-quantity);
						if(item3.getQuantity()<0) {
							throw new FoodCartException("reduce Quantity should be less than actual quantity");
						}

						ido.save(item3);

						break;
					}
				}
				cart.setItemList(items);
				fdo.save(cart);

				udo.save(user);

				return cart;


			} else {
				throw new FoodCartException("login as a customer");
			}

		} else {
			throw new ItemException("no item available with id :" + itemId);
		}
	}

	@Override
	public FoodCart removeItemFromCart(Integer itemId, String key) {
		Optional<Item> item = ido.findById(itemId);

		if (item.isPresent()) {

			CurrentUserSession cus = sdo.findByUuid(key);

			if(cus==null) {
				throw new UserException("enter valid key");
			}

			UserType uType = cus.getType();

			if (uType.name() == "Customer") {
				User user = udo.findById(cus.getUserId()).orElseThrow(() -> new UserException("No user found"));

				FoodCart cart = user.getCart();

				cart.getItemList().remove(item.get());

				udo.save(user);

				return cart;

			} else {
				throw new FoodCartException("login as a customer");
			}

		} else {
			throw new ItemException("no item available with id :" + itemId);
		}

	}

	@Override
	public String removeAllItemFromCart(String key) {
		CurrentUserSession cus = sdo.findByUuid(key);

		if(cus==null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Customer") {


			User user = udo.findById(cus.getUserId()).orElseThrow(() -> new UserException("No user found"));

			FoodCart cart = user.getCart();

			if(cart==null) {
				throw new FoodCartException("cart is already empty");
			}

			if (cart.getItemList().isEmpty()) {
				throw new FoodCartException("cart is already empty");
			} else {
				cart.getItemList().clear();

				udo.save(user);

				return "your cart is now empty";
			}

		}

		throw new FoodCartException("login as a customer");

	}

	
	@Override
	public FoodCart viewCart(String key) {

		CurrentUserSession cus = sdo.findByUuid(key);
		
		if(cus==null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Customer") {

			User user = udo.findById(cus.getUserId()).orElseThrow(() -> new UserException("No user found"));

			FoodCart cart = user.getCart();
			
			if(cart==null) {
				throw new FoodCartException("cart is empty");
			}

			if (cart.getItemList().isEmpty()) {
				throw new FoodCartException("cart is already empty");
			} else {

				return cart;
			}

		}

		throw new FoodCartException("login as a customer");

	}
}
