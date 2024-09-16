package com.example.demo.Patron;

import com.example.demo.Borrowing.BorrowingRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Table(name = "Patron")
@Entity
public class Patron {

    
    @Id
    @SequenceGenerator(
            name = "patron_sequence",
            sequenceName = "patron_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patron_sequence"
    )
    private Long id;
    @NotBlank(message = "cannot be empty")
    private String name;
    private String contactInformation;
    @OneToMany(mappedBy = "patron")
    private List<BorrowingRecord> borrowingRecords;

    public Patron() {
    }

    public Patron(long l, String johnDoe, String number) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }


}
