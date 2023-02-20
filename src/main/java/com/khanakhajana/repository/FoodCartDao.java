package com.khanakhajana.repository;

import com.khanakhajana.model.FoodCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCartDao extends JpaRepository<FoodCart, Integer> {

}
