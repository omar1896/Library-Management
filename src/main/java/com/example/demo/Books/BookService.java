package com.example.demo.Books;


import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();

    }

    @Cacheable(value = "books", key = "#bookId")
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));

    }

    public void CreateNewBook(Book book) {
        bookRepository.save(book);
    }

    public void DeleteBook(Long bookId) {
        Book existedBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        if (existedBook != null) {
            bookRepository.delete(existedBook);
        }
    }

    @CachePut(value = "books", key = "#book.id")
    public void updateBook(Long bookId, Book updatedBook) throws EntityNotFoundException {

        Book existedBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        if (existedBook != null) {
            existedBook.setTitle(updatedBook.getTitle());
            existedBook.setAuthor(updatedBook.getAuthor());
            existedBook.setIsbn(updatedBook.getIsbn());
            existedBook.setPublicationYear(updatedBook.getPublicationYear());
            bookRepository.save(existedBook);
        }
    }

    public boolean checkBorrowedBooks(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));

        return false;
    }

    public void updateBookAvalability(Book book, boolean flag) {
        if (book.isAvailable()) {
            book.setAvailable(flag);
        } else {
            book.setAvailable(flag);
        }
        bookRepository.save(book);
    }

    public Book findAvailableBookById(Long bookId) {
        Book book = findBookById(bookId);  // Assume this is a method in your BookService
        if (book == null) {
            throw new EntityNotFoundException("Book not found");
        }
        if (!book.isAvailable()) {
            throw new EntityNotFoundException("Book is already borrowed");
        }
        return book;
    }
}
