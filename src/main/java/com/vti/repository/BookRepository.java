package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vti.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    
	@Query(value = "select * from Book where id = :id ", nativeQuery = true)
	Book GetById(@Param("id") int id); 
	
	@Query(value = "SELECT * FROM book where name like %:name% ", nativeQuery = true)
	List<Book> AdvancedSearch(@Param("name") String name);
	// paging 
	@Query(value = "select * from ( SELECT ROW_NUMBER() OVER(order by id desc) as row_index, id, author, description, image, name, publication_year, category_id from Book "
			+ " where name like %:name% ) "
			+ " as tb where row_index >= ( :pageIndex - 1) * :pageSize + 1 and row_index <= :pageIndex * :pageSize ", nativeQuery = true)
	List<Book> AdvancedSearchAndPaging(@Param("name") String name, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize); 
}
