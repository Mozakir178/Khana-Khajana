package com.khanakhajana.service;

import com.khanakhajana.exception.CategoryException;
import com.khanakhajana.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

	public Category addCategory(Category category,String key) throws CategoryException;
	public  Category updateCategory(Category category,String key) throws CategoryException;
	public Category removeCategory(Category category,String key) throws CategoryException;
	public Category viewCategory(Category category,String key) throws CategoryException;
	public List<Category> viewAllCategory(String key) throws CategoryException;
}
