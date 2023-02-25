package com.khanakhajana.service.implementation;

import com.khanakhajana.exception.FoodCartException;
import com.khanakhajana.exception.OrderDetailException;
import com.khanakhajana.exception.RestaurantException;
import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.*;
import com.khanakhajana.repository.*;
import com.khanakhajana.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private UserDao udo;

	@Autowired
	private RestaurantDao rdo;

	@Autowired
	private SessionDao sdo;

	@Autowired
	private OrderDetailDao odo;
	
	@Autowired
	private BillDao bdo;

	@Override
	public OrderDetail addDetails(String key) {

		CurrentUserSession cus = sdo.findByUuid(key);

		if (cus == null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Customer") {

			User user = udo.findById(cus.getUserId()).get() ;

			FoodCart cart = user.getCart();
		

			List<Item> items = cart.getItemList();

			if (items.isEmpty()) {
				throw new FoodCartException("your cart is empty");
			}
			
			

			OrderDetail od = new OrderDetail();

			od.setOrderDate(LocalDateTime.now());
			od.setCart(cart);
			od.setStatus(false);
			od.setRestaurant(items.get(0).getRestaurant());
			od.setCustomer(user);
			
			cart.getItemList().clear();

			return odo.save(od);

		} else {
			throw new UserException("login as a customer");
		}

	}

	@Override
	public String cancelOrderByRestaurant(Integer orderId, String key) throws RestaurantException {
		CurrentUserSession cus = sdo.findByUuid(key);
		
		if (cus == null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Restaurent") {

			Restaurant res = rdo.findByMobile(cus.getMobile());

			List<OrderDetail> orderDetails = res.getOrderDetails();

			boolean od1 = true;

			for (int i = 0; i < orderDetails.size(); i++) {
				if (orderDetails.get(i).getOrderId() == orderId) {
					
					od1 = false;
					orderDetails.get(i).setStatus(false);
					orderDetails.get(i).setRestaurant(null);
					odo.save(orderDetails.get(i));
					orderDetails.remove(i);
					break;
				}
			}
			
			
			
			res.setOrderDetails(orderDetails);

			if (!od1) {
				rdo.save(res);
			}

			if (od1) {
				throw new OrderDetailException("no order available with this id : " + orderId);
			}

			return "order cancel successfuly...";

		} else {
			throw new RestaurantException("login as a Restaurant");
		}

	}

	@Override
	public String cancelOrderByCustomer(Integer orderId, String key) {

		Optional<OrderDetail> order = odo.findById(orderId);

		if (order.isPresent()) {

			OrderDetail od = order.get();

			User customer = od.getCustomer();

			CurrentUserSession cus = sdo.findByUserId(customer.getUserId()) ;

			if (cus == null) {
				throw new UserException("enter valid key");
			}

			if (cus.getUuid().equals(key)) {

				odo.delete(od);

				return "order deleted successfully...";

			} else {
				throw new OrderDetailException("this order is not your order");
			}

		} else {
			throw new OrderDetailException("no order available with id : " + orderId);
		}

	}

	@Override
	public OrderDetail acceptOrderByRestaurant(Integer orderId, String key) throws RestaurantException {
		CurrentUserSession cus = sdo.findByUuid(key);

		if (cus == null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Restaurent") {

			Restaurant res = rdo.findByMobile(cus.getMobile());

			List<OrderDetail> orderDetails = res.getOrderDetails();

			OrderDetail od1 = null;

			for (OrderDetail o : orderDetails) {
				if (o.getOrderId() == orderId) {
					o.setStatus(true);
					od1 = o;
					odo.delete(o);
				}
			}
			
			User cus2 =	od1.getCustomer();
			
			List<Item> items = od1.getCart().getItemList();
			Bill bill= new Bill();
			bill.setBillDate(LocalDateTime.now());
			bill.setTotalitems(items.size());
			double cost = 0;
			for (Item item : items) {
				cost+=item.getCost();
			}
			bill.setTotalCost(cost);
			bill.setCustomer(cus2);
			
			
			
			bdo.save(bill);
			
			od1.setCustomer(null);

			return odo.save(od1);

		} else {
			throw new RestaurantException("login as a Restaurant");
		}

	}

	@Override
	public List<OrderDetail> viewAllOrderByRestaurant(String key) {
		CurrentUserSession cus = sdo.findByUuid(key);

		if (cus == null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Restaurent") {

			Restaurant res = rdo.findByMobile(cus.getMobile());

			List<OrderDetail> orderDetails = res.getOrderDetails();

			if (orderDetails.isEmpty()) {
				throw new OrderDetailException("no order available right now");
			}

			return orderDetails;

		} else {
			throw new RestaurantException("login as a Restaurant");
		}

	}

	@Override
	public List<OrderDetail> viewAllOrderByCustomer(String key) throws OrderDetailException, UserException {

		CurrentUserSession cus = sdo.findByUuid(key);

		if (cus == null) {
			throw new UserException("enter valid key");
		}

		UserType uType = cus.getType();

		if (uType.name() == "Customer") {

			User customer = udo.findById(cus.getUserId()).get() ;

			List<OrderDetail> orderDetails = customer.getOrderDetails();
			
			if (orderDetails.isEmpty()) {
				throw new OrderDetailException("no order available right now");
			}
			

			return orderDetails;

		} else {
			throw new RestaurantException("login as a customer");
		}
	}

}
