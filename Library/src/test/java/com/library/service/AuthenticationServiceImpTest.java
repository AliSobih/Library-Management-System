package com.library.service;

import com.library.dto.AuthenticationRequest;
import com.library.dto.AuthenticationResponse;
import com.library.dto.RegisterRequest;
import com.library.entity.Patron;
import com.library.entity.Token;
import com.library.repository.PatronRepo;
import com.library.repository.TokenRepository;
import com.library.security.jwt.JWTService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;




@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImpTest {

    @Mock
    private PatronRepo patronRepo;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private AuthenticationServiceImp authenticationService;

    @Test
    void testBadRequestRegister() {
        RegisterRequest registerRequest = new RegisterRequest("test", "test", "password", "test@gmail.com", "1234567890");
        Patron existingPatron = new Patron();
        existingPatron.setEmail("test@gmail.com");
        given(patronRepo.findByEmail("test@gmail.com")).willReturn(Optional.of(existingPatron));
        AuthenticationResponse response = authenticationService.register(registerRequest);
        assertTrue(response.getToken().isEmpty());
    }

    @Test
    void testSuccessRegister() {
        RegisterRequest registerRequest
                = new RegisterRequest("test", "test", "password", "test@gmail.com", "1234567890");
        given(patronRepo.save(any())).willReturn(new Patron());
        given(jwtService.generateToken(any())).willReturn("testToken");
        given(patronRepo.save(any())).willReturn(new Patron());
        given(tokenRepository.save(any())).willReturn(new Token());
        AuthenticationResponse response = authenticationService.register(registerRequest);
        assertEquals("testToken", response.getToken());
    }

    @Test
    void testBadRequestLogin() {
        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("test@gmail.com", "password");
        Patron existingPatron = new Patron();
        existingPatron.setEmail("badEmail@gmail.com");
        AuthenticationResponse response = authenticationService.login(authenticationRequest);
        assertTrue(response.getToken().isEmpty());
    }

    @Test
    void testLogin() {
        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("test@gmail.com", "password");
        Patron existingPatron = new Patron();
        existingPatron.setEmail("test@gmail.com");
        given(patronRepo.findByEmail(authenticationRequest.getEmail())).willReturn(Optional.of(existingPatron));
        given(jwtService.generateToken(any())).willReturn("testToken");
        given(patronRepo.save(any())).willReturn(new Patron());
        given(tokenRepository.save(any())).willReturn(new Token());
        AuthenticationResponse response = authenticationService.login(authenticationRequest);
        assertEquals("testToken", response.getToken());
    }
}