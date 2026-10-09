package com.futurehire.controller;

import com.futurehire.dto.request.AuthRequest;
import com.futurehire.dto.request.RegisterRequest;
import com.futurehire.dto.response.ApiResponse;
import com.futurehire.dto.response.AuthResponse;
import com.futurehire.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authService.registerStudent(registerRequest);
        return new ResponseEntity<>(ApiResponse.success("Student registered successfully", response), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
}
