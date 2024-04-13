package com.library.service;

import com.library.entity.Book;
import com.library.entity.BorrowingRecord;
import com.library.entity.Patron;
import com.library.repository.BorrowingRecordRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceImpTest {

    @Mock
    private BorrowingRecordRepo borrowingRecordRepo;

    @InjectMocks
    private BorrowingServiceImp borrowingService;

    @Test
    public void testBorrowBook() {
        Book book = new Book();
        Patron patron = new Patron();
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        given(borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(book, patron)).willReturn(Optional.empty());
        given(borrowingRecordRepo.save(any())).willReturn(borrowingRecord);

        BorrowingRecord result = borrowingService.borrowBook(book, patron);
        assertNotNull(result);
        assertEquals(book, borrowingRecord.getBook());
        assertEquals(patron, borrowingRecord.getPatron());
        assertNotNull(borrowingRecord.getBorrowDate());
    }

    @Test
    public void testBadRequestBorrowBook() {
        Book book = new Book();
        Patron patron = new Patron();
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        given(borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(book, patron)).willReturn(Optional.of(borrowingRecord));
        BorrowingRecord result = borrowingService.borrowBook(book, patron);
        assertNull(result);
    }

    @Test
    public void testReturnBook() throws InterruptedException {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(new Book());
        borrowingRecord.setPatron(new Patron());
        borrowingRecord.setBorrowDate(LocalDate.now());
        given(borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(any(), any())).willReturn(Optional.of(borrowingRecord));
        given(borrowingRecordRepo.save(any())).willReturn(borrowingRecord);
        BorrowingRecord returnedRecord = borrowingService.returnBook(borrowingRecord.getBook(), borrowingRecord.getPatron());
        assertNotNull(returnedRecord.getReturnDate());
        verify(borrowingRecordRepo, times(1)).save(borrowingRecord);
    }

    @Test
    public void testReturnBook_RecordNotFound() {
        given(borrowingRecordRepo.findByBookAndPatronAndReturnDateIsNull(any(), any())).willReturn(Optional.empty());
        Book book = new Book();
        Patron patron = new Patron();
        BorrowingRecord returnedRecord = borrowingService.returnBook(book, patron);
        assertNull(returnedRecord);
    }
}