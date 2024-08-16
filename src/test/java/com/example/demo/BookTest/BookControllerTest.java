package com.example.demo.BookTest;

import com.example.demo.Books.Book;
import com.example.demo.Books.BookController;
import com.example.demo.Books.BookService;
import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book testBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Effective Java");
    }

    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of(testBook));

        ResponseEntity<?> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        assertEquals(1, ((List<?>) response.getBody()).size());
    }

    @Test
    void testGetBookById() {
        when(bookService.findBookById(anyLong())).thenReturn(testBook);

        ResponseEntity<?> response = bookController.getBookById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testBook, response.getBody());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookService.findBookById(anyLong())).thenThrow(new EntityNotFoundException("Book not found"));

        ResponseEntity<?> response = bookController.getBookById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    void testAddNewBook() {
        ResponseEntity<String> response = bookController.addNewBook(testBook);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Book created successfully", response.getBody());
        verify(bookService, times(1)).CreateNewBook(testBook);
    }

    @Test
    void testRemoveBook() {
        doNothing().when(bookService).DeleteBook(anyLong());

        ResponseEntity<String> response = bookController.removeBook(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book deleted successfully", response.getBody());
    }
}
