package com.example.myApp.auth.service;

import com.example.myApp.auth.dto.LandlordRegisterRequest;
import com.example.myApp.auth.dto.LoginRequest;
import com.example.myApp.auth.dto.LoginResponse;

public interface AuthService {
  void registerLandlord(LandlordRegisterRequest request);

  LoginResponse login(LoginRequest request);
}
