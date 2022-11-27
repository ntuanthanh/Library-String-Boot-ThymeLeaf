package com.vti.service.IService;

import java.util.List;

import com.vti.model.Book;

public interface IBookService {
    public void add(Book book);
    public void update(Book book); 
    public List<Book> getAllBook(); 
    public Book getBookById(int id); 
    public void DeleteBook(int id);
    public List<Book> searchBook(String name); 
    public List<Book> searchBookAndPaging(String name, int pageIndex, int pageSize);  
}
