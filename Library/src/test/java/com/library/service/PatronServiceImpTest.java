package com.library.service;

import com.library.entity.Patron;
import com.library.repository.PatronRepo;
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
class PatronServiceImpTest {

    @Mock
    private PatronRepo patronRepo;

    @InjectMocks
    private PatronServiceImp patronService;

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = Arrays.asList(new Patron(), new Patron());
        given(patronRepo.findAll()).willReturn(patrons);
        List<Patron> result = patronService.getAllPatrons();
        assertEquals(patrons, result);
    }

    @Test
    public void testGetPatronById() {
        Long id = 1L;
        Patron patron = new Patron();
        patron.setId(id);
        given(patronRepo.findById(id)).willReturn(Optional.of(patron));
        Optional<Patron> result = patronService.getPatronById(id);
        assertEquals(Optional.of(patron), result);
    }

    @Test
    public void testGetPatronByIdNotFound() {
        Long id = 1L;
        Patron patron = new Patron();
        patron.setId(id);
        given(patronRepo.findById(id)).willReturn(null);
        Optional<Patron> result = patronService.getPatronById(id);
        assertNull(result);
    }

    @Test
    public void testSavePatron() {
        Patron patron = new Patron();
        given(patronRepo.save(patron)).willReturn(patron);
        Patron result = patronService.savePatron(patron);
        assertEquals(patron, result);
    }

    @Test
    public void testDeletePatron() {
        Long id = 1L;
        patronService.deletePatron(id);
        verify(patronRepo, times(1)).deleteById(id);
    }
}