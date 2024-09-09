package com.example.demo.Books;

import com.example.demo.Borrowing.BorrowingRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.lang.annotation.RequiredTypes;

import java.util.List;

@Table(name = "Book")
@Entity
public class Book {



    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    @NotBlank(message = "cannot be empty")
    private String title;

    private String author;

    private int publicationYear;

    @Size(min = 5, message = "ISBN must be at least 5 characters long")
    private String isbn;

    @OneToMany(mappedBy = "book")
    private List<BorrowingRecord> borrowingRecords;
    @Column(nullable = false)
    private boolean available = true;

    public Book() {
    }

    public Book(long l, String bookTitle, boolean b) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}



