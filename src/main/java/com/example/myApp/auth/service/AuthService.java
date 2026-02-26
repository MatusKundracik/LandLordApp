package com.example.myApp.auth.service;

import com.example.myApp.auth.dto.LandlordRegisterRequest;
import com.example.myApp.auth.dto.LoginRequest;
import com.example.myApp.auth.dto.LoginResponse;
import com.example.myApp.auth.dto.TenantRegisterRequest;

public interface AuthService {
  void registerLandlord(LandlordRegisterRequest request);

  LoginResponse login(LoginRequest request);

  void registerTenant(TenantRegisterRequest request);
}
