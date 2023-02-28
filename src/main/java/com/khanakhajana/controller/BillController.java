package com.khanakhajana.controller;

import com.khanakhajana.model.Bill;
import com.khanakhajana.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/bills")
public class BillController {

	@Autowired
	private BillService billService;

	@GetMapping("bill/{key}")
	public ResponseEntity<Bill> viewBill(@PathVariable("key") String key) {

		return new ResponseEntity<Bill>(billService.viewBill(key), HttpStatus.OK);

	}

}
