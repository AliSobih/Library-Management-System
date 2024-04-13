package com.library.repository;

import com.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepo extends JpaRepository<Patron, Long> {
    Optional<Patron> findByEmail(String email);
}
