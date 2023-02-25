package com.khanakhajana.service;

import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {


	public User addUser(User user)throws UserException;

	public User updateUser(User user)throws UserException;

	public User removeUser(Integer userId)throws UserException;

	public User viewUser(Integer userId)throws UserException;

	public List<User> viewAllUser()throws UserException;




}
