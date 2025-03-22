package com.library.controller;

import com.library.entity.Borrow;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrowBook")
    public Borrow borrowBook(@RequestParam int userId, @RequestParam int bookId) {
        return borrowService.borrowBook(userId, bookId);
    }

    @PutMapping("/returnBook")
    public Borrow returnBook(@RequestParam int borrowId) {
        return borrowService.returnBook(borrowId);
    }

}
