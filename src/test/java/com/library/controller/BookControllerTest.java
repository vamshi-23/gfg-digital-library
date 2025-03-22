package com.library.controller;

import com.library.entity.Books;
import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private BookController MakeBookControllerWithMockService(BookService mockService) {
        BookController bookController = new BookController();
        bookController.bookService = mockService;
        return bookController;
    }

    @Test
    public void SortedByType_ReturnsSortedBooksList() throws Exception {
        // Arrange
        Books book1 = new Books(1, "Title A", "Author A");
        Books book2 = new Books(2, "Title B", "Author B");
        List<Books> sortedBooks = Arrays.asList(book1, book2);
        when(bookService.getSortedByColumn("title")).thenReturn(sortedBooks);

        // Act & Assert
        mockMvc.perform(get("/book/sort").param("column", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title A"))
                .andExpect(jsonPath("$[1].title").value("Title B"));
    }

    @Test
    public void SortedByType_InvalidColumn_HandlesGracefully() throws Exception {
        // Arrange
        when(bookService.getSortedByColumn("invalidColumn")).thenThrow(new IllegalArgumentException("Invalid column"));

        // Act & Assert
        mockMvc.perform(get("/book/sort").param("column", "invalidColumn"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void SortedByType_CallsBookServiceWithCorrectParameter() {
        // Arrange
        BookService mockService = mock(BookService.class);
        BookController bookController = MakeBookControllerWithMockService(mockService);

        // Act
        bookController.sortedByType("author");

        // Assert
        verify(mockService).getSortedByColumn("author");
    }
}