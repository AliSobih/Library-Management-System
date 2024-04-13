package com.library.service;


import com.library.dto.AuthenticationRequest;
import com.library.dto.AuthenticationResponse;
import com.library.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
