package com.library.repo;

import java.awt.print.Book;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer>{
	
	//save()
	@Query("SELECT b FROM Books b  WHERE b.bname LIKE %:keywords% or b.bauthor LIKE %:keywords% or b.btype LIKE %:keywords% ")
	
	List<Books> searchBooks(@Param("keywords") String keywords);
	
	//FIlter by Type
	List<Books> findByBtype(String btype);
	//Sorting
	List<Books> findAllByOrderByBnameAsc();
    List<Books> findAllByOrderByBauthorAsc();
    List<Books> findAllByOrderByBpulicationyearAsc();

}
