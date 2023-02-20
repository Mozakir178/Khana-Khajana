package com.khanakhajana.service;

import com.khanakhajana.dto.Login;
import com.khanakhajana.exception.UserLoginException;

public interface LoginService {

    public String logIntoCustomer(Login login) throws UserLoginException;

    public String logIntoRestaurent(Login login) throws UserLoginException;

    public String logOutFromSession(String key) throws UserLoginException;



}
