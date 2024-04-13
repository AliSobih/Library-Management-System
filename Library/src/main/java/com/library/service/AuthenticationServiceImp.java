package com.library.service;


import com.library.dto.AuthenticationRequest;
import com.library.dto.AuthenticationResponse;
import com.library.dto.RegisterRequest;
import com.library.entity.Patron;
import com.library.entity.Token;
import com.library.repository.PatronRepo;
import com.library.repository.TokenRepository;
import com.library.security.jwt.JWTService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService{
    private final PatronRepo patronRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepo;

    @Transactional
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Patron patron = patronRepo.findByEmail(request.getEmail()).orElse(null);
        if (patron != null) {
            return AuthenticationResponse.builder().token("").build();
        }
        patron = Patron.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        String jwtToken = jwtService.generateToken(patron);
        Token token = new Token(jwtToken);
        tokenRepo.save(token);
        patron.addToken(token);
        patronRepo.save(patron);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Patron patron = patronRepo.findByEmail(request.getEmail()).orElse(null);
        if (patron == null) {
            return AuthenticationResponse.builder().token("").build();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword(),
                        patron.getAuthorities()
                )
        );

        String jwtToken = jwtService.generateToken(patron);
        Token token = new Token(jwtToken);
        patron.addToken(token);
        tokenRepo.save(token);
        patronRepo.save(patron);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
