package com.bnt.compentancy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.entity.Category;
import com.bnt.compentancy.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/insert")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		
		Category category1= categoryService.addCategory(category);
		
		return ResponseEntity.ok(category1);
		
	}
	
	@GetMapping("/getId/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId){
		
		return this.categoryService.getCategory(categoryId);
		
	}
	
	@GetMapping("/GetAllCategories")
	public ResponseEntity<?> getCategory(){
		
		return ResponseEntity.ok(this.categoryService.getCategory());
		
	}
	
	@PutMapping("/updateCategory")
	public Category updateCategory(@RequestBody Category category) {
		
		return this.categoryService.updateCategory(category);
		
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}

	
}
