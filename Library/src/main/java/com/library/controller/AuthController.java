package com.library.controller;


import com.library.dto.AuthenticationRequest;
import com.library.dto.AuthenticationResponse;
import com.library.dto.RegisterRequest;
import com.library.service.AuthenticationService;
import com.library.service.AuthenticationServiceImp;
import com.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationServiceImp;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authenticationServiceImp.register(request), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationServiceImp.login(request));
    }
}
