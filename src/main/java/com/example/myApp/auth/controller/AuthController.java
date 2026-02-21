package com.example.myApp.auth.controller;

import com.example.myApp.auth.dto.LandlordRegisterRequest;
import com.example.myApp.auth.dto.LoginRequest;
import com.example.myApp.auth.dto.LoginResponse;
import com.example.myApp.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/landlord")
    public ResponseEntity<String> registerLandlord(@Valid @RequestBody LandlordRegisterRequest request) {
        authService.registerLandlord(request);
        return ResponseEntity.ok("Landlord registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

