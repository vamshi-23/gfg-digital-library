package com.library.service;

import com.library.entity.Books;
import com.library.entity.Borrow;
import com.library.entity.User;
import com.library.repo.BookRepository;
import com.library.repo.BorrowRepository;
import com.library.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository booksRepository;

    public Borrow borrowBook(int userId, int bookId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Books books = booksRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBooks(books);
        borrow.setIssueDate(new Date());

        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(int borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(()->new RuntimeException("Borrow details not found"));
        borrow.setReturnDate(new Date());
        return borrowRepository.save(borrow);
    }
}
