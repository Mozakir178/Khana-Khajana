package com.khanakhajana.repository;

import com.khanakhajana.exception.UserException;
import com.khanakhajana.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    public User findByMobile(String mobile) ;
}
