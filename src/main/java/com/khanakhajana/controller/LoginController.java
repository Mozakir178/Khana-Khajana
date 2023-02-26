package com.khanakhajana.controller;


import com.khanakhajana.model.LoginDTO;
import com.khanakhajana.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/loginCustomer")
    public ResponseEntity<String > LogIntoCustomerHandler(@RequestBody LoginDTO dto){

        String message = loginService.logIntoCustomer(dto);

        return new ResponseEntity<String>(message, HttpStatus.OK);

    }


    @PostMapping("/loginRestaurent")
    public ResponseEntity<String > LogIntoRestaurentHandler(@RequestBody LoginDTO dto){

        String message = loginService.logIntoRestaurent(dto);

        return new ResponseEntity<String>(message, HttpStatus.OK);

    }

    @DeleteMapping("/logoutUser/{key}")
    public ResponseEntity<String > LogOutHandler(@PathVariable("key") String  key){

        String message = loginService.logOutFromSession(key);

        return new ResponseEntity<String>(message, HttpStatus.OK);

    }

}
