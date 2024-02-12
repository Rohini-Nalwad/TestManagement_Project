package com.testController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.controller.CategoryController;
import com.entity.Category;
import com.exception.CategoryNotFoundException;
import com.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void testAddNewCategory() {
        Category category = new Category();
        when(categoryService.addCategory(any(Category.class))).thenReturn(category);

        Category result = categoryController.addNewCategory(category);

        assertEquals(category, result);
    }

    @Test
    public void testGetAllCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        when(categoryService.getAllCatogory()).thenReturn(categories);

        ArrayList<Category> result = categoryController.getAllCategory();

        assertEquals(categories, result);
    }

    @Test
    public void testGetCategoryById_Exists() throws CategoryNotFoundException {
        Long categoryId = 1L;
        Category category = new Category();
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);

        ResponseEntity<?> result = categoryController.getCategoryById(categoryId);

        assertEquals(ResponseEntity.ok(category), result);
    }

    @Test
    public void testGetCategoryById_NotFound() throws CategoryNotFoundException {
        Long categoryId = 1L;
        when(categoryService.getCategoryById(categoryId)).thenThrow(CategoryNotFoundException.class);

        ResponseEntity<?> result = categoryController.getCategoryById(categoryId);

        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with ID: " + categoryId), result);
    }
    @Test
    public void testUpdateCategory() {
        Long categoryId = 1L;
        Category updatedCategory = new Category();
        updatedCategory.setCategoryId(categoryId);

        when(categoryService.exists(categoryId)).thenReturn(true);
        when(categoryService.updateCategory(updatedCategory)).thenReturn(updatedCategory);

        ResponseEntity<?> result = categoryController.updateCategory(categoryId, updatedCategory);

        assertEquals(ResponseEntity.ok(updatedCategory), result);
    }

  
    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;

        when(categoryService.exists(categoryId)).thenReturn(true);

        ResponseEntity<?> result = categoryController.deleteCategory(categoryId);

        assertEquals(ResponseEntity.ok().build(), result);
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }
}