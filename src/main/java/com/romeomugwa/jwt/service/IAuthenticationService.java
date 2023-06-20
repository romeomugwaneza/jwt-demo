package com.romeomugwa.jwt.service;

import com.romeomugwa.jwt.auth.LoginRequest;
import com.romeomugwa.jwt.auth.RegisterResponse;
import com.romeomugwa.jwt.auth.RegisterRequest;

public interface IAuthenticationService {


    Object authenticate(LoginRequest authenticationRequest);

    RegisterResponse register(RegisterRequest registerRequest);
}
