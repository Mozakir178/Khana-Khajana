package com.khanakhajana.repository;

import com.khanakhajana.model.Bill;
import com.khanakhajana.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository<Bill, Integer> {
		
	public Bill findByCustomer(User customer);
	
}
