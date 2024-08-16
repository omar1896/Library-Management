package BorrowingRecordTest;

import com.example.demo.Books.Book;
import com.example.demo.Books.BookService;
import com.example.demo.Borrowing.BorrowingRecordController;
import com.example.demo.Borrowing.BorrowingRecordService;
import com.example.demo.Patron.Patron;
import com.example.demo.Patron.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_shouldReturnSuccessWhenServiceSucceeds() {

        doNothing().when(borrowingRecordService).createBorrowingRecord(anyLong(), anyLong());


        ResponseEntity<String> response = borrowingRecordController.createBooking(1L, 1L);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking created successfully", response.getBody());
    }

    @Test
    void returnBook_shouldReturnSuccessWhenServiceSucceeds() {

        doNothing().when(borrowingRecordService).returnBook(anyLong(), anyLong());


        ResponseEntity<String> response = borrowingRecordController.returnBook(1L, 1L);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book returned successfully", response.getBody());
    }

    @Test
    void createBooking_shouldReturnErrorWhenServiceFails() {

        doThrow(new RuntimeException("Booking failure")).when(borrowingRecordService).createBorrowingRecord(anyLong(), anyLong());


        ResponseEntity<String> response = borrowingRecordController.createBooking(1L, 1L);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to create booking: Booking failure", response.getBody());
    }

    @Test
    void returnBook_shouldReturnErrorWhenServiceFails() {

        doThrow(new RuntimeException("Return failure")).when(borrowingRecordService).returnBook(anyLong(), anyLong());


        ResponseEntity<String> response = borrowingRecordController.returnBook(1L, 1L);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to return book: Return failure", response.getBody());
    }
}
