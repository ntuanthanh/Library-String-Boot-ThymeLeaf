package com.vti.controller.v1;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vti.controller.base.BaseController;
import com.vti.model.Book;
import com.vti.model.Category;
import com.vti.service.IService.IBookService;
import com.vti.service.IService.ICategoryService;

@BaseController
public class BookController {
    
	@Autowired
	private IBookService bookService; 
	@Autowired
    private ICategoryService categoryService; 
	
	// Show form to add
	@GetMapping("/add")
    public String showAddBook(Model model) {
		// Send Category
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories); 
		return "book/addBook"; 
    }
	// Add new Book
	@PostMapping("/add")
	public String addBook(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("image") String image, @RequestParam("author") String author, @RequestParam("publicationYear") int publicationYear, @RequestParam("category_id") int categoryId) {
		Book book = new Book(); 
		book.setImage(image); 
		book.setAuthor(author); 
		book.setDescription(description); 
		book.setPublicationYear(publicationYear); 
		book.setName(name); 
		// Category 
		Category category = new Category(); 
		category.setId(categoryId); 
		book.setCategory(category); 
		// Save
		bookService.add(book); 
		return "redirect:list"; 
	}
	// Show all list
	@GetMapping("list")
	public String listBook(Model model) {
		List<Book> books = bookService.getAllBook(); 
		model.addAttribute("books",books); 
		return "book/listBook"; 
	}
	// Delete
	@GetMapping("delete")
	public String deleteBook(@RequestParam("id") int book_id) {
		bookService.DeleteBook(book_id);
		return "redirect:list"; 
	}
	// Detail 
	@GetMapping("detail")
	public String detailBook(@RequestParam("id") int book_id, Model model) {
		Book book = bookService.getBookById(book_id);
		model.addAttribute("book", book);
		return "book/detailBook"; 
	}
	// Show form update
	@GetMapping("update")
	public String showUpdateBook(@RequestParam("id") int book_id, Model model) {
		// Category 
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		// Old book 
		Book book = bookService.getBookById(book_id); 
		model.addAttribute("book",book); 
		return "book/updateBook"; 
	}
	// Update normal using @RequestParam and file value; 
//	@PostMapping("update")
//	public String updateBook(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("description") String description,
//			@RequestParam("image") String image, @RequestParam("author") String author, @RequestParam("publicationYear") int publicationYear, @RequestParam("category_id") int categoryId) {
//		// Book
//		Book book = new Book();		
//		book.setId(id);
//		book.setImage(image); 
//		book.setAuthor(author); 
//		book.setDescription(description); 
//		book.setPublicationYear(publicationYear); 
//		book.setName(name); 
//		// Category
//		Category category = new Category(); 
//		category.setId(categoryId); 
//		book.setCategory(category);
//		// Save 
//		bookService.update(book);
//		return "redirect:list"; 
//	}
	// Update book
	@PostMapping("update")
	public String updateBook(@ModelAttribute Book book,Model model) {
		bookService.update(book);
		return "redirect:list"; 
	}
	// Advanced Search 
	@GetMapping("search")
	public String advancedSearchBook(@RequestParam(value = "name_search", required = false) String name, Model model) {
		// Service 
		List<Book> books = bookService.searchBook(name); 
		model.addAttribute("books", books); 
		model.addAttribute("historySearch", name); 
		// Result 
		return "book/listSearchBook"; 
	}
	// Advanced Search and Paging
	@GetMapping("search/v2")
	public String advancedSearchAndPaging(@RequestParam(value = "name_search", required =  false) String name, @RequestParam(value = "page", required = false) Integer pageIndex,Model model, HttpServletRequest request) {
		// Service
		// Fix cứng pageSize; 
		if(pageIndex == null) {
			pageIndex = 1; 
		}
		int pageSize = 5;
		List<Book> books = bookService.searchBookAndPaging(name, pageIndex, pageSize);
		int count = bookService.searchBook(name).size(); 
		int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1; 
		// Lấy url để phân trang 
		String url = "v2?"; 
		String url_param = request.getQueryString();
		if(url_param != null && url_param.length() > 0) {
			if(url_param.endsWith("page=" + pageIndex)) {
				url_param = url_param.replaceAll("page=" + pageIndex,""); 
			}
			// th book?page=x thiếu & nên thêm vào 
			if(!url_param.equals("") && !url_param.endsWith("&")) {
				url_param += "&"; 
			}
			url += (url_param); 
		} 
		// Đã lấy được url để phân trang 
		model.addAttribute("books",books);
		model.addAttribute("historySearch", name);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageIndex", pageIndex); 
		model.addAttribute("url", url); 
//		System.out.println(url); 
		// Paging bằng js
		return "book/listSearchBookV2"; 
	}
}
