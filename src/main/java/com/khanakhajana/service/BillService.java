package com.khanakhajana.service;

import com.khanakhajana.exception.BillException;
import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.Bill;

public interface BillService {
	
	public Bill viewBill(String Key) throws BillException,UserException;

}
