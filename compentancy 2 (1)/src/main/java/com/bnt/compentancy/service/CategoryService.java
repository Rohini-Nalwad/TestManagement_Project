package com.bnt.compentancy.service;

import java.util.Set;

import com.bnt.compentancy.model.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getCategory();
	
	public Category getCategory(Long categoryId);
	
	public void deleteCategory(Long category);

}
