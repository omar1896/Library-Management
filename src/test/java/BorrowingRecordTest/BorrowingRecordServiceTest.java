package BorrowingRecordTest;

import com.example.demo.Books.Book;
import com.example.demo.Books.BookService;
import com.example.demo.Borrowing.BorrowingRecord;
import com.example.demo.Borrowing.BorrowingRecordRpository;
import com.example.demo.Borrowing.BorrowingRecordService;
import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import com.example.demo.Patron.Patron;
import com.example.demo.Patron.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRpository borrowingRecordRpository;

    @Mock
    private BookService bookService;

    @Mock
    private PatronService patronService;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBorrowingRecord_shouldCreateRecordWhenBookAndPatronExist() {

        Book book = new Book(1L, "Book Title", true);
        Patron patron = new Patron(1L, "Patron Name", "123456");
        BorrowingRecord borrowingRecord = new BorrowingRecord();

        when(patronService.getPatronById(anyLong())).thenReturn(patron);
        when(bookService.findAvailableBookById(anyLong())).thenReturn(book);
        doNothing().when(borrowingRecordRpository).save(any(BorrowingRecord.class));
        doNothing().when(bookService).updateBookAvalability(any(Book.class), anyBoolean());


        borrowingRecordService.createBorrowingRecord(1L, 1L);


        verify(borrowingRecordRpository, times(1)).save(any(BorrowingRecord.class));
        verify(bookService, times(1)).updateBookAvalability(book, false);
    }

    @Test
    void returnBook_shouldUpdateBookAvailabilityAndRecordReturnDate() {
        // Arrange
        Book book = new Book(1L, "Book Title", false);
        Patron patron = new Patron(1L, "Patron Name", "123456");
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        when(borrowingRecordRpository.getBorrowingRecordByBookIdAndPatronId(anyLong(), anyLong())).thenReturn(borrowingRecord);
        when(bookService.findBookById(anyLong())).thenReturn(book);
        doNothing().when(borrowingRecordRpository).save(any(BorrowingRecord.class));
        doNothing().when(bookService).updateBookAvalability(any(Book.class), anyBoolean());


        borrowingRecordService.returnBook(1L, 1L);


        verify(borrowingRecordRpository, times(1)).save(any(BorrowingRecord.class));
        verify(bookService, times(1)).updateBookAvalability(book, true);
    }

    @Test
    void createBorrowingRecord_shouldThrowExceptionWhenBookNotFound() {

        when(patronService.getPatronById(anyLong())).thenReturn(new Patron(1L, "Patron Name", "123456"));
        when(bookService.findAvailableBookById(anyLong())).thenThrow(new EntityNotFoundException("Book not found"));


        assertThrows(EntityNotFoundException.class, () -> borrowingRecordService.createBorrowingRecord(1L, 1L));
    }
}
