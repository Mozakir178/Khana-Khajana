package com.khanakhajana.repository;

import com.khanakhajana.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {


}
