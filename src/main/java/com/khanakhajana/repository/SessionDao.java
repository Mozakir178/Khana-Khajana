package com.khanakhajana.repository;

import com.khanakhajana.model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDao extends JpaRepository<CurrentUserSession, Integer> {

    public CurrentUserSession findByUuid(String uuid);

    public CurrentUserSession findByUserId(Integer userId);


}
