package com.vti.service.IService;

import java.util.List;

import com.vti.model.Category;

public interface ICategoryService {
	public void add(Category category);
    public void update(Category category); 
    public List<Category> getAllCategory(); 
    public Category getCategoryById(int id); 
    public void DeleteCategory(int id); 
}
