package com.arun.blog.services;

import java.util.List;

import com.arun.blog.payloads.CategoryDto;

public interface CategoryService {

	// create 
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updatecategory(CategoryDto categoryDto ,Integer categoryId);
	
	//delete
	public void deletecategory(Integer categoryId);
	
	// get 
	public CategoryDto getCategory(Integer categoryId);
	
	//getAll
	public List<CategoryDto> getCategories();
	
}
