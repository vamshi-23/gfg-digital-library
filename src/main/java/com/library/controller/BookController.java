package com.library.controller;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping("/search/{keyword}")
	public List<Books> searchBooks(@PathVariable String keyword)
	{
		return bookService.searchBooks(keyword);
	}
	
	@GetMapping("/filterByType/{btype}")
    public List<Books> filterBooksByType(@PathVariable String btype) {
        return bookService.filterBooksByType(btype);
    }
	
	@GetMapping("/sortByTitle")
    public List<Books> sortBooksByTitle() {
        return bookService.sortBooksByTitle();
    }

    @GetMapping("/sortByAuthor")
    public List<Books> sortBooksByAuthor() {
        return bookService.sortBooksByAuthor();
    }

    @GetMapping("/sortByPublicationYear")
    public List<Books> sortBooksByPublicationYear() {
        return bookService.sortBooksByPublicationYear();
    }
}
