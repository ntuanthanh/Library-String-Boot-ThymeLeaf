package com.vti.service.EService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.model.Book;
import com.vti.repository.BookRepository;
import com.vti.service.IService.IBookService;

@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void add(Book book) {
	   bookRepository.save(book);     
	}

	@Override
	public void update(Book book) {
		bookRepository.save(book); 
	}

	@Override
	public List<Book> getAllBook() {
		 List<Book> books = bookRepository.findAll(); 
		 return books; 
	}

	@Override
	public Book getBookById(int id) {
		return bookRepository.GetById(id);
	}

	@Override
	public void DeleteBook(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> searchBook(String name) {
		return bookRepository.AdvancedSearch(name); 
	}

	@Override
	public List<Book> searchBookAndPaging(String name, int pageIndex, int pageSize) {
		List<Book> books = bookRepository.AdvancedSearchAndPaging(name, pageIndex, pageSize); 
		return books; 
	}
}
