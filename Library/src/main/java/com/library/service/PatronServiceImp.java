package com.library.service;

import com.library.entity.Patron;
import com.library.repository.PatronRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatronServiceImp implements PatronService {
    private final PatronRepo patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
