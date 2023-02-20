package com.khanakhajana.repository;

import com.khanakhajana.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item,Integer>{
	public List<Item> findByItemName(String name);
//	public List<Item> findByCategory();
}
