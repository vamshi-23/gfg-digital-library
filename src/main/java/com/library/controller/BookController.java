package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.entity.Books;
import com.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	@PostMapping("/add")
	public Books saveBook(@RequestBody Books book)
	{
		return bookService.addBook(book);
	}
	@GetMapping("/all")
	public List<Books> allBooks()
	{
		return bookService.getAllBook();
	}
	@GetMapping("/one/{id}")
	public Optional<Books> getSingleBook(@PathVariable int id)
	{
		return bookService.getSingleBooks(id);
	}
	@PutMapping("/edit/{id}")
	public Books updateBooks(@PathVariable int id,@RequestBody Books book)
	{
		return bookService.updateBooks(id, book);
	}
	@DeleteMapping("del/{id}")
	public void delBook(@PathVariable int id)
	{
		bookService.deleteBook(id);
	}
	@GetMapping("sort/{column}")
	public List<Books> sortedByType(@RequestParam String column){
		return bookService.getSortedByColumn(column);
	}
}
