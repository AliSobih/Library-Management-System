package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
    private final BookRepo bookRepo;

    @Override
    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    @Cacheable("books")
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }
}
