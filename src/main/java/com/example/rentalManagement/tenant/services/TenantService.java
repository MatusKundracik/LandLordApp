package com.example.rentalManagement.tenant.services;

import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import com.example.rentalManagement.tenant.entity.Tenant;
import java.util.List;

public interface TenantService {

  TenantResponseDto getMyProfile(String email);

  TenantResponseDto updateMyProfile(TenantRequestDto request, String email);

  void deleteMyProfile(String email);

  Tenant getTenantByEmail(String email);

  List<TenantResponseDto> getAllTenantsByApartmentId(Long apartmentId, String email);
}
