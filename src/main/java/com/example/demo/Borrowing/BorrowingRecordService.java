package com.example.demo.Borrowing;

import com.example.demo.Books.Book;
import com.example.demo.Books.BookService;
import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import com.example.demo.Patron.Patron;
import com.example.demo.Patron.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {
    private final BorrowingRecordRpository BorrowingRecordRpository;
    private final BookService bookService;
    private final PatronService patronService;

    @Autowired
    public BorrowingRecordService(com.example.demo.Borrowing.BorrowingRecordRpository borrowingRecordRpository, BookService bookService, PatronService patronService) {
        BorrowingRecordRpository = borrowingRecordRpository;
        this.bookService = bookService;
        this.patronService = patronService;
    }


    public void saveBooking(BorrowingRecord borrowingRecord) {
        BorrowingRecordRpository.save(borrowingRecord);
    }

    public BorrowingRecord getBorrowingRecordByBookIdAndPatronId(Long BookId, Long patronId) {
        BorrowingRecord borrowingRecord = BorrowingRecordRpository.getBorrowingRecordByBookIdAndPatronId(BookId, patronId);
        if (borrowingRecord == null) {
            throw new EntityNotFoundException("borrowingRecord" + " not found");
        }
        return borrowingRecord;
    }
    @Transactional
    public void createBorrowingRecord(Long bookId, Long patronId) {
        Patron patron = patronService.getPatronById(patronId);
        Book book = bookService.findAvailableBookById(bookId);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        saveBooking(borrowingRecord);
        //update Book to be unavailable
        bookService.updateBookAvalability(book, false);
    }
    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        Book book = bookService.findBookById(bookId);
        BorrowingRecord borrowingRecord = getBorrowingRecordByBookIdAndPatronId(bookId, patronId);
        borrowingRecord.setReturnDate(LocalDate.now());
        saveBooking(borrowingRecord);
        //update Book to be available
        bookService.updateBookAvalability(book, true);
    }
}
