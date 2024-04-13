package com.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(name = "publication_year")
    private String publicationYear;

    @Column(name = "isbn")
    private String ISBN;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

    //convenience method
    public void addBorrowingRecord(BorrowingRecord record) {
        record.setBook(this);
        borrowingRecords.add(record);
    }
}
