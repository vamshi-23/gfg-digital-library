package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entity.Books;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer>{

    @Query("SELECT b FROM Books b WHERE b.bname LIKE '%:keyword%' OR b.bauthor LIKE '%:keyword%' OR b.btype LIKE '%:keyword%'")
    List<Books> searchBook(@Param("keyword") String keyword);

}
