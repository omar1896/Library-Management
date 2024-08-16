package com.example.demo.BookTest;

import com.example.demo.Books.Book;
import com.example.demo.Books.BookRepository;
import com.example.demo.Books.BookService;
import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Effective Java");
        testBook.setAvailable(true);
    }

    @Test
    void testFindBookById() {
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.of(testBook));

        Book book = bookService.findBookById(1L);
        assertNotNull(book);
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    void testFindBookByIdNotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            bookService.findBookById(1L);
        });
        assertEquals("Book with ID 1 not found", thrown.getMessage());
    }

    @Test
    void testCreateNewBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        bookService.CreateNewBook(testBook);

        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        testBook.setTitle("Updated Title");
        bookService.updateBook(1L, testBook);

        verify(bookRepository, times(1)).save(testBook);
    }
}
