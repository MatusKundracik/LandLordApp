package com.example.rentalManagement.auth.controller;

import com.example.rentalManagement.auth.dto.LandlordRegisterRequest;
import com.example.rentalManagement.auth.dto.LoginRequest;
import com.example.rentalManagement.auth.dto.LoginResponse;
import com.example.rentalManagement.auth.dto.TenantRegisterRequest;
import com.example.rentalManagement.auth.service.AuthService;
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
  public ResponseEntity<String> registerLandlord(
      @Valid @RequestBody LandlordRegisterRequest request) {
    authService.registerLandlord(request);
    return ResponseEntity.ok("Landlord registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/register/tenant")
  public ResponseEntity<String> registerTenant(@Valid @RequestBody TenantRegisterRequest request) {
    authService.registerTenant(request);
    return ResponseEntity.ok("Tenant registered successfully");
  }
}
