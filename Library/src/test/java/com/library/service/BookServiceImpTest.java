package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceImpTest {
    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookServiceImp bookServiceImp;

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        given(bookRepo.findAll()).willReturn(books);
        List<Book> result = bookServiceImp.getAllBooks();
        assertEquals(books, result);
    }

    @Test
    void testGetBookById() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        given(bookRepo.findById(id)).willReturn(Optional.of(book));
        Optional<Book> result = bookServiceImp.getBookById(id);
        assertEquals(Optional.of(book), result);
    }

    @Test
    void testGetBookByIdNotFound() {
        Long id = 1L;
        given(bookRepo.findById(id)).willReturn(Optional.empty());
        Optional<Book> result = bookServiceImp.getBookById(id);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        given(bookRepo.save(book)).willReturn(book);
        Book result = bookServiceImp.saveBook(book);
        assertEquals(book, result);
    }

    @Test
    void testDeleteBookById() {
        Long id = 1L;
        bookServiceImp.deleteBookById(id);
        verify(bookRepo, times(1)).deleteById(id);
    }
}