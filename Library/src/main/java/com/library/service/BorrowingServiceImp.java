package com.library.service;

import com.library.entity.Book;
import com.library.entity.BorrowingRecord;
import com.library.entity.Patron;
import com.library.repository.BorrowingRecordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImp implements BorrowingService {

    private final BorrowingRecordRepo borrowingRecordRepo;

    @Transactional
    public BorrowingRecord borrowBook(Book book, Patron patron) {
        BorrowingRecord borrowingRecord = borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(book, patron).orElse(null);
        if (borrowingRecord != null) {
           return null;
        }
        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        return borrowingRecordRepo.save(borrowingRecord);
    }


    public BorrowingRecord returnBook(Book book, Patron patron) {
        BorrowingRecord borrowingRecord = borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(book, patron).orElse(null);
        if (borrowingRecord != null) {
            borrowingRecord.setReturnDate(LocalDate.now());
            return borrowingRecordRepo.save(borrowingRecord);
        }
        return null;
    }
}
