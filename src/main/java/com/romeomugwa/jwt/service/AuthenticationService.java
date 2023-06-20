package com.romeomugwa.jwt.service;

import com.romeomugwa.jwt.auth.LoginResponse;
import com.romeomugwa.jwt.domain.User;
import com.romeomugwa.jwt.domain.Role;
import lombok.RequiredArgsConstructor;
import com.romeomugwa.jwt.config.JwtService;
import org.springframework.stereotype.Service;
import com.romeomugwa.jwt.auth.RegisterRequest;
import com.romeomugwa.jwt.repository.UserRepository;
import com.romeomugwa.jwt.auth.LoginRequest;
import com.romeomugwa.jwt.auth.RegisterResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        if (userRepository
                .findByEmail(registerRequest.getEmail())
                .isPresent()) return null;

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return RegisterResponse.builder()
                .message("success")
                .build();
    }
}
