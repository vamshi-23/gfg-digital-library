package com.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.entity.Books;
import com.library.repo.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	public Books addBook(Books myBook)
	{
		return bookRepo.save(myBook);
	}
	public List<Books> getAllBook()
	{
		return bookRepo.findAll();
	}
	public Optional<Books> getSingleBooks(int id)
	{
		return bookRepo.findById(id);
	}
	public Books updateBooks(int id, Books book)
	{
		Optional<Books> theBook=bookRepo.findById(id);
		Books newBook=theBook.get();
		newBook.setBname(book.getBname());
		newBook.setBauthor(book.getBauthor());
		newBook.setBpulicationyear(book.getBpulicationyear());
		newBook.setBtype(book.getBtype());
		return bookRepo.save(newBook);
		
	}
	public void deleteBook(int id)
	{
		bookRepo.deleteById(id);
	}
	public List<Books> searchBooks(String keyword){
		return bookRepo.searchBook(keyword);
	}
	public List<Books> getSortedByColumn(String column){
		return bookRepo.findAll(Sort.by(Sort.Direction.ASC, column));
	}
}
