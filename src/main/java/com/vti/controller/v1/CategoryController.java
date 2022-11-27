package com.vti.controller.v1;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vti.controller.base.BaseController;
import com.vti.model.Category;
import com.vti.service.IService.ICategoryService;

@BaseController
public class CategoryController {
   
   @Autowired
   private ICategoryService categoryService; 
   
   // Show List Category 
   @GetMapping("/category/list")
   public String showListCategry(Model model) {
	   // list all 
	   List<Category> categories = categoryService.getAllCategory(); 
	   model.addAttribute("categories",categories); 
	   return "listCategory"; 
   }
   // Show Add
   @GetMapping("/category/add")
   public String addCategory() {
	   return "addCategory"; 
   }
   //Post
   @PostMapping("/category/add")
   public String addCategory(@RequestParam("name") String name) {
	   //Business logic 
	   Category category = new Category(); 
	   category.setName(name); 
	   // Add
	   categoryService.add(category); 
	   System.out.print(name); 
	   return "redirect:list"; 
   }
   //Delete 
   @GetMapping("/category/delete")
   public String delete(@RequestParam("id") String raw_id) {
	   // chưa validate 
	   try {
		   int id = Integer.parseInt(raw_id); 
		   categoryService.DeleteCategory(id); 
		   return "redirect:list";   
	   }catch(Exception e) {
		   return "/ErrorPage"; 
	   }
    }
}
