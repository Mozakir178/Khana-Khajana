package com.khanakhajana.service.implementation;

import com.khanakhajana.exception.BillException;
import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.Bill;
import com.khanakhajana.model.CurrentUserSession;
import com.khanakhajana.model.User;
import com.khanakhajana.model.UserType;
import com.khanakhajana.repository.*;
import com.khanakhajana.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private FoodCartDao fdo;

	@Autowired
	private ItemDao ido;

	@Autowired
	private SessionDao sdo;

	@Autowired
	private UserDao udo;

	@Autowired
	private BillDao bdo;

	@Override
	public Bill viewBill(String key) throws BillException, UserException {

		CurrentUserSession cus = sdo.findByUuid(key);

		UserType uType = cus.getType();

		if (uType.name() == "Customer") {

			User user = udo.findById(cus.getUserId()).orElseThrow( () -> new UserException("Please Login First")) ;

			Bill bill = bdo.findByCustomer(user);

			if (bill == null) {
				throw new BillException("no bill available for this customer");
			}

			return bill;

		} else {
			throw new UserException("login as a customer");
		}

	}

}
