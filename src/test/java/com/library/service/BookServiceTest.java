package com.library.service;

import com.library.entity.Books;
import com.library.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    private BookRepository mockBookRepo;
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        mockBookRepo = mock(BookRepository.class);
        bookService = new BookService();
        bookService.bookRepo = mockBookRepo;
    }

    private BookService MakeBookServiceWithMockRepo(BookRepository mockRepo) {
        BookService bookService = new BookService();
        bookService.bookRepo = mockRepo;
        return bookService;
    }

    @Test
    public void AddBook_SavesAndReturnsBook() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService bookService = MakeBookServiceWithMockRepo(mockRepo);
        Books book = new Books();
        book.setBname("Test Book");

        when(mockRepo.save(book)).thenReturn(book);

        // Act
        Books result = bookService.addBook(book);

        // Assert
        assertEquals(book, result);
        verify(mockRepo).save(book);
    }

    @Test
    public void GetAllBook_ReturnsAllBooks() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService bookService = MakeBookServiceWithMockRepo(mockRepo);
        Books book1 = new Books();
        Books book2 = new Books();
        List<Books> books = Arrays.asList(book1, book2);

        when(mockRepo.findAll()).thenReturn(books);

        // Act
        List<Books> result = bookService.getAllBook();

        // Assert
        assertEquals(books, result);
        verify(mockRepo).findAll();
    }

    @Test
    public void UpdateBooks_UpdatesAndReturnsUpdatedBook() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService bookService = MakeBookServiceWithMockRepo(mockRepo);
        Books existingBook = new Books();
        existingBook.setBname("Old Name");
        Books updatedBook = new Books();
        updatedBook.setBname("New Name");

        when(mockRepo.findById(1)).thenReturn(Optional.of(existingBook));
        when(mockRepo.save(existingBook)).thenReturn(existingBook);

        // Act
        Books result = bookService.updateBooks(1, updatedBook);

        // Assert
        assertEquals("New Name", result.getBname());
        verify(mockRepo).findById(1);
        verify(mockRepo).save(existingBook);
    }
    @Test
    public void GetSortedByColumn_SortsBooksByGivenColumn() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService service = MakeBookServiceWithMockRepo(mockRepo);
        Books book1 = new Books("Book A", "Author A", 2000, "Type A");
        Books book2 = new Books("Book B", "Author B", 1999, "Type B");
        List<Books> books = Arrays.asList(book1, book2);
        when(mockRepo.findAll(Sort.by(Sort.Direction.ASC, "Bname"))).thenReturn(books);

        // Act
        List<Books> result = service.getSortedByColumn("Bname");

        // Assert
        assertEquals(books, result);
    }

    @Test
    public void GetSortedByColumn_WithEmptyList_ReturnsEmptyList() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService service = MakeBookServiceWithMockRepo(mockRepo);
        when(mockRepo.findAll(Sort.by(Sort.Direction.ASC, "Bname"))).thenReturn(Collections.emptyList());

        // Act
        List<Books> result = service.getSortedByColumn("Bname");

        // Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void GetSortedByColumn_CallsFindAllWithCorrectParameters() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService service = MakeBookServiceWithMockRepo(mockRepo);

        // Act
        service.getSortedByColumn("Bname");

        // Assert
        verify(mockRepo).findAll(Sort.by(Sort.Direction.ASC, "Bname"));
    }
}