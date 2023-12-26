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

import com.bnt.compentancy.model.Category;
import com.bnt.compentancy.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		
		Category category1= categoryService.addCategory(category);
		
		return ResponseEntity.ok(category1);
		
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId){
		
		return this.categoryService.getCategory(categoryId);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getCategory(){
				
		return ResponseEntity.ok(this.categoryService.getCategory());
		
	}
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		
		return this.categoryService.updateCategory(category);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}

	
}
