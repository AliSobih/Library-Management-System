package com.library.service;

import com.library.entity.Book;
import com.library.entity.BorrowingRecord;
import com.library.entity.Patron;

public interface BorrowingService {
    public BorrowingRecord borrowBook(Book book, Patron patron);
    public BorrowingRecord returnBook(Book book, Patron patron);
}
