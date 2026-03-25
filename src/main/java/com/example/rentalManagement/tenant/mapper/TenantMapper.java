package com.example.rentalManagement.tenant.mapper;

import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

  public Tenant toEntity(TenantRequestDto request, User user, Landlord landlord) {
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
        .email(request.getEmail())
        .user(user)
        .landlord(landlord)
        .build();
  }

  public TenantResponseDto toDto(Tenant tenant) {
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
        .email(tenant.getEmail())
        .createdAt(tenant.getCreatedAt())
        .updatedAt(tenant.getUpdatedAt())
        .build();
  }
}
