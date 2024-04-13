package com.library.repository;

import com.library.entity.Book;
import com.library.entity.BorrowingRecord;
import com.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}
