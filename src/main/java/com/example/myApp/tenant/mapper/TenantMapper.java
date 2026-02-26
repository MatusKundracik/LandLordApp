package com.example.myApp.tenant.mapper;

import com.example.myApp.tenant.dtos.TenantRequestDto;
import com.example.myApp.tenant.dtos.TenantResponseDto;
import com.example.myApp.tenant.entity.Tenant;
import com.example.myApp.user.entity.User;

public class TenantMapper {

  public static Tenant toEntity(TenantRequestDto request, User user) {
    return Tenant.builder()
        .name(request.getName())
        .surname(request.getSurname())
        .dateOfBirth(request.getDateOfBirth())
        .street(request.getStreet())
        .streetNumber(request.getStreetNumber())
        .city(request.getCity())
        .postalCode(request.getPostalCode())
        .country(request.getCountry())
        .phoneNumber(request.getPhoneNumber())
        .user(user)
        .build();
  }

  public static TenantResponseDto toDto(Tenant tenant) {
    return TenantResponseDto.builder()
        .id(tenant.getId())
        .name(tenant.getName())
        .surname(tenant.getSurname())
        .dateOfBirth(tenant.getDateOfBirth())
        .street(tenant.getStreet())
        .streetNumber(tenant.getStreetNumber())
        .city(tenant.getCity())
        .postalCode(tenant.getPostalCode())
        .country(tenant.getCountry())
        .phoneNumber(tenant.getPhoneNumber())
        .createdAt(tenant.getCreatedAt())
        .updatedAt(tenant.getUpdatedAt())
        .build();
  }
}
