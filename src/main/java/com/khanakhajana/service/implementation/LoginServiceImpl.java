package com.khanakhajana.service.implementation;

import com.khanakhajana.exception.UserLoginException;
import com.khanakhajana.model.CurrentUserSession;
import com.khanakhajana.model.LoginDTO;
import com.khanakhajana.model.Restaurant;
import com.khanakhajana.model.User;
import com.khanakhajana.repository.RestaurantDao;
import com.khanakhajana.repository.SessionDao;
import com.khanakhajana.repository.UserDao;
import com.khanakhajana.service.LoginService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao udao;
    @Autowired
    private SessionDao sdao;
   @Autowired
   private RestaurantDao rdao;



    @Override
    public String logIntoCustomer(LoginDTO dto) throws UserLoginException {
        User existingUser = udao.findByMobile(dto.getMobile());

        if(existingUser == null){
            throw  new UserLoginException("User not exist with this mobile no. plz create Account");
        }

        Optional<CurrentUserSession> validSession = Optional.ofNullable(sdao.findByUserId(existingUser.getUserId()));

        if(validSession.isPresent()){
            throw new UserLoginException("User already logged in with this Number");


        }

        if(existingUser.getPassword().equals(dto.getPassword())){

            String key = RandomString.make(8);

            CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserId(), existingUser.getMobile(),key,
                    LocalDateTime.now(),existingUser.getType());

            sdao.save(currentUserSession);

            return currentUserSession.toString();
        }else {

            throw new UserLoginException("Please enter valid password");
        }


    }

    @Override
    public String logIntoRestaurent(LoginDTO dto) throws UserLoginException {

        Restaurant existingUser = rdao.findByMobile(dto.getMobile());
        if(existingUser == null){
            throw  new UserLoginException("User not exist with this mobile no. plz create Account");
       }

       Optional<CurrentUserSession> validSession = Optional.ofNullable(sdao.findByUserId(existingUser.getRestaurantaid()));

       if(validSession.isPresent()){
           throw new UserLoginException("User already logged in with this Number");


        }

        if(existingUser.getPassword().equals(dto.getPassword())){

            String key = RandomString.make(8);



            CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getRestaurantaid() ,existingUser.getMobile(),key,

                    LocalDateTime.now(),existingUser.getType());



            sdao.save(currentUserSession);



            return currentUserSession.toString();

        }else {



            throw new UserLoginException("Please enter valid password");

        }

    }

    @Override
    public String logOutFromSession(String key) throws UserLoginException {

        CurrentUserSession currentUserSession = sdao.findByUuid(key);

        if(currentUserSession == null){
             throw new UserLoginException("User Not logged in with this Number");
        }

        sdao.delete(currentUserSession);

        return "Logged out";
    }


}
