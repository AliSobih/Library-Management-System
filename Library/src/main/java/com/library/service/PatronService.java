package com.library.service;

import com.library.entity.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    public List<Patron> getAllPatrons();
    public Optional<Patron> getPatronById(Long id);
    public Patron savePatron(Patron patron);
    public void deletePatron(Long id);

}
