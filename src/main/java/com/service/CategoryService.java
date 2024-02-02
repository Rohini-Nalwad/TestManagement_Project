package com.service;

import java.util.ArrayList;
import java.util.Optional;

import com.entity.Category;

public interface CategoryService {

	Category addCategory(Category category);

	ArrayList<Category> getAllCatogory();

	Category updateCategory(Category category);

	void deleteCategory(Long categoryId);

	boolean exists(Long categoryId);

	Category getCategoryByTitle(String categoryTitle);

	Category getCategoryById(Long categoryId);

	

}
