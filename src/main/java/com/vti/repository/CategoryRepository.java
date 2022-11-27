package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>  {
   // native query
	// Sử dụng @Query Native ( Cách 1 : dùng ?1 để setId )
    @Query(value = "SELECT * FROM category where id = ?1;", nativeQuery = true)
	Category myGetById(int id); 
}
