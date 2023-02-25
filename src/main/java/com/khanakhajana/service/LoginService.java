package com.khanakhajana.service;

import com.khanakhajana.dto.Login;
import com.khanakhajana.exception.UserLoginException;
import com.khanakhajana.model.LoginDTO;

public interface LoginService {

    public String logIntoCustomer(LoginDTO dto) throws UserLoginException;

    public String logIntoRestaurent(LoginDTO dto) throws UserLoginException;

    public String logOutFromSession(String key) throws UserLoginException;



}
