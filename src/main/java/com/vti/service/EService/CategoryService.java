package com.vti.service.EService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.model.Category;
import com.vti.repository.CategoryRepository;
import com.vti.service.IService.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
   
	@Autowired
    private CategoryRepository categoryRepository; 
	
	@Override
	public void add(Category category) {
	  categoryRepository.save(category); 
	}

	@Override
	public void update(Category category) {
		categoryRepository.save(category); 		
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category getCategoryById(int id) {
	    Category category = categoryRepository.myGetById(id); 
		return category; 
	}

	@Override
	public void DeleteCategory(int id) {
		categoryRepository.deleteById(id);
	}
}
