package com.library.controller;

import com.library.entity.Patron;
import com.library.service.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = patronService.getPatronById(id);
        return patron.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
        Patron savedPatron = patronService.savePatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patron) {
        if (!patronService.getPatronById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patron.setId(id);
        Patron updatedPatron = patronService.savePatron(patron);
        return ResponseEntity.ok(updatedPatron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        if (!patronService.getPatronById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
