package com.example.demo.Borrowing;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRpository extends JpaRepository<BorrowingRecord, Long> {

   BorrowingRecord getBorrowingRecordByBookIdAndPatronId(Long bookId, Long patronId);
}
