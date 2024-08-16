package com.example.demo.Borrowing;

import com.example.demo.Books.Book;
import com.example.demo.Books.BookService;
import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import com.example.demo.Patron.Patron;
import com.example.demo.Patron.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping
public class BorrowingRecordController {
    private final BorrowingRecordService BorrowingRecordService;
    private final BookService bookService;
    private final PatronService patronService;

    @Autowired
    public BorrowingRecordController(com.example.demo.Borrowing.BorrowingRecordService borrowingRecordService, BookService bookService, PatronService patronService) {
        BorrowingRecordService = borrowingRecordService;
        this.bookService = bookService;
        this.patronService = patronService;
    }

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> createBooking(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecordService.createBorrowingRecord(bookId, patronId);
            return new ResponseEntity<>("Booking created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create booking: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecordService.returnBook(bookId, patronId);
            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to return book: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
