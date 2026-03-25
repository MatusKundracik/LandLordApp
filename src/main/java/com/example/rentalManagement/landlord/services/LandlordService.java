package com.example.rentalManagement.landlord.services;

import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.dtos.LandlordResponseDto;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import java.util.List;

public interface LandlordService {

  LandlordResponseDto getMyProfile(String email);

  LandlordResponseDto updateMyProfile(LandlordRequestDto dto, String email);

  void deleteMyProfile(String email);

  Landlord getLandlordByEmail(String email);

  TenantResponseDto createTenant(TenantRequestDto dto);

  List<TenantResponseDto> getAllLandlordTenants(String email);

  void deleteTenant(String email, Long id);
}
