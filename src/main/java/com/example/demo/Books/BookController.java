package com.example.demo.Books;


import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            if (books.isEmpty()) {
                return new ResponseEntity<>("No books available", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve books: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.findBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Book not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewBook(@Valid @RequestBody Book newBook) {
        try {
            bookService.CreateNewBook(newBook);
            return new ResponseEntity<>("Book created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create book: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBook(@PathVariable Long id) {
        try {
            bookService.DeleteBook(id);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Book not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        try {
            bookService.updateBook(id, bookDetails);
            return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Book not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}