package com.example.myApp.tenant.services;

import com.example.myApp.tenant.dtos.TenantRequestDto;
import com.example.myApp.tenant.dtos.TenantResponseDto;

public interface TenantService {

  TenantResponseDto getMyProfile(String email);

  TenantResponseDto updateMyProfile(TenantRequestDto request, String email);

  void deleteMyProfile(String email);
}
