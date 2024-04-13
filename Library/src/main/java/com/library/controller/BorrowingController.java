package com.library.controller;

import com.library.entity.Book;
import com.library.entity.BorrowingRecord;
import com.library.entity.Patron;
import com.library.service.BookService;
import com.library.service.BorrowingService;
import com.library.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;
    private final BookService bookService;
    private final PatronService patronService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Book book = bookService.getBookById(bookId).orElse(null);
        Patron patron = patronService.getPatronById(patronId).orElse(null);
        if (book == null || patron == null) {
            return ResponseEntity.notFound().build();
        }
        BorrowingRecord borrowingRecord = borrowingService.borrowBook(book, patron);
        if (borrowingRecord == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Book book = bookService.getBookById(bookId).orElse(null);
        Patron patron = patronService.getPatronById(patronId).orElse(null);
        if (book == null || patron == null) {
            return ResponseEntity.notFound().build();
        }
        BorrowingRecord borrowingRecord = borrowingService.returnBook(book, patron);
        if (borrowingRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
