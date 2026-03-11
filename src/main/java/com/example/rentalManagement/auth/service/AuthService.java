package com.example.rentalManagement.auth.service;

import com.example.rentalManagement.auth.dto.LandlordRegisterRequest;
import com.example.rentalManagement.auth.dto.LoginRequest;
import com.example.rentalManagement.auth.dto.LoginResponse;
import com.example.rentalManagement.auth.dto.TenantRegisterRequest;

public interface AuthService {
  void registerLandlord(LandlordRegisterRequest request);

  LoginResponse login(LoginRequest request);

  void registerTenant(TenantRegisterRequest request);
}
