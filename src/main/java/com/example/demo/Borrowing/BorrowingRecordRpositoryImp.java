package com.example.demo.Borrowing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BorrowingRecordRpositoryImp implements BorrowingRecordRpository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BorrowingRecord getBorrowingRecordByBookIdAndPatronId(Long bookId, Long patronId) {
        String jpql = "SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.patron.id = :patronId";
        TypedQuery<BorrowingRecord> query = entityManager.createQuery(jpql, BorrowingRecord.class);
        query.setParameter("bookId", bookId);
        query.setParameter("patronId", patronId);
        return query.getSingleResult();
    }
}
