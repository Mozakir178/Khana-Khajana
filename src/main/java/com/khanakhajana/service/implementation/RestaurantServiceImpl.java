package com.khanakhajana.service.implementation;

import com.khanakhajana.exception.RestaurantException;
import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.CurrentUserSession;
import com.khanakhajana.model.Restaurant;
import com.khanakhajana.model.UserType;
import com.khanakhajana.repository.RestaurantDao;
import com.khanakhajana.repository.SessionDao;
import com.khanakhajana.service.RestaurentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurentService {

	@Autowired
	private RestaurantDao rdo;

	@Autowired
	private SessionDao sdo;

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		
		return rdo.save(restaurant);
		
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant, String key) throws RestaurantException {



		CurrentUserSession cus = sdo.findByUuid(key);
		
		if(cus==null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name().equals("Restaurant")) {

			if(restaurant==null){
				throw new RestaurantException("Please Enter Valid Restaurant");
			}else {
				return rdo.save(restaurant);

			}


		} else {
			throw new RestaurantException("login as a Restaurant");
		}

	}



	@Override
	public Restaurant removeRestaurant(Restaurant restaurant, String key) throws RestaurantException {
		CurrentUserSession cus = sdo.findByUuid(key);
		
		if(cus==null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name().equals("Restaurant")) {

           if(restaurant==null){
			   throw  new RestaurantException("Please Enter Valid Restaurant");
		   }else{
			   Optional<Restaurant> existingRestaurant =rdo.findById(restaurant.getRestaurantaid());
			   if(existingRestaurant.isPresent()){
				   rdo.delete(existingRestaurant.get());
				   return existingRestaurant.get();
			   }else{
				   throw new RestaurantException("Restaurant Does not Exist");
			   }
		   }

		} else {
			throw new RestaurantException("login as a Restaurant");
		}

	}

	@Override
	public Restaurant viewRestaurant(Integer restaurant, String key) throws RestaurantException {
		CurrentUserSession cus = sdo.findByUuid(key);
		
		if(cus==null) {
			throw new UserException("enter valid key");
		}

		Restaurant restaurant1 = rdo.findByMobile(cus.getMobile()) ;
		System.out.println(restaurant1);

		if (restaurant1 != null) {

			if(restaurant==null){
				throw  new RestaurantException("Please Enter Valid Restaurant Id");
			}else{
				Optional<Restaurant> existingRestaurant =rdo.findById(restaurant);
				if(existingRestaurant.isPresent()){
					return existingRestaurant.get();
				}else{
					throw new RestaurantException("Restaurant Does not Exist");
				}
			}
		} else {
			throw new RestaurantException("login as a Restaurant");
		}

	}

	@Override
	public List<Restaurant> viewRestaurantByLocation(String location, String key) throws RestaurantException {
		CurrentUserSession cus = sdo.findByUuid(key);
		
		if(cus==null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name().equals("Customer")) {

			List<Restaurant> rlist=rdo.findByAddress(location);
			if(rlist.isEmpty()){
				throw  new RestaurantException("No Restaurant available with this Address : "+location);
			}else{
				return rlist;
			}
		} else {
			throw new UserException("login as a Customer");
		}
	}

	@Override
	public List<Restaurant> viewRestaurantsByItemName(String name, String key) throws RestaurantException {
		CurrentUserSession cus = sdo.findByUuid(key);
		
		if(cus==null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name().equals("Customer")) {

        List<Restaurant> rlist=rdo.findByItems(name);
		if(rlist.isEmpty()){
			throw  new RestaurantException("No Restaurant available with this Name : "+name);
		}else{
			return rlist;
		}

		} else {
			throw new UserException("login as a Customer");
		}

	}
}
