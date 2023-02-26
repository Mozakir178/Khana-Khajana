package com.khanakhajana.service.implementation;

import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.CurrentUserSession;
import com.khanakhajana.model.User;
import com.khanakhajana.repository.SessionDao;
import com.khanakhajana.repository.UserDao;
import com.khanakhajana.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	private UserDao udao;

	@Autowired
	private SessionDao sdao;
	
	@Override
	public User addUser(User user) {


		Optional<User> checkUser = udao.findById(user.getUserId()) ;
		if(checkUser.isPresent())
			throw new UserException("User Already registered");
		
		return udao.save(user);
	}

	@Override
	public User updateUser(User user) throws UserException {
		CurrentUserSession existUser = sdao.findByUserId( user.getUserId());

		if(existUser == null)
			throw new UserException("Plz login with this number : "+ user.getMobile());

		User user1 = udao.save(user);

		return user1;

	}

	@Override
	public User removeUser(Integer userId) throws UserException {
		CurrentUserSession existUser =sdao.findByUserId(userId) ;

		if(existUser == null)
			throw new UserException("Plz login with this : "+ userId);

		User user1 = udao.findById(userId).orElseThrow(() -> new UserException("Please login first"));
		udao.delete(user1);
		sdao.delete(existUser);
		return user1;

	}

	@Override
	public User viewUser(Integer userId) throws UserException {
		CurrentUserSession existUser = sdao.findByUserId(userId) ;

		if(existUser == null)
			throw new UserException("Plz login with this id : "+ userId);

		User user1 = udao.findById(userId).get() ;
		return user1;
	}

	@Override
	public List<User> viewAllUser() throws UserException {

		List<User> user1 = udao.findAll();

		if(user1.isEmpty()){
			throw new UserException("No user found");
		}


		return user1;
	}

}
