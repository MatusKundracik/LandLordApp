package com.example.rentalManagement.user.services;

import com.example.rentalManagement.exception.LandlordNotFoundException;
import com.example.rentalManagement.exception.TenantNotFoundException;
import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.entity.Landlord;
import com.example.rentalManagement.landlord.mapper.LandlordMapper;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.entity.Tenant;
import com.example.rentalManagement.tenant.mapper.TenantMapper;
import com.example.rentalManagement.tenant.repository.TenantRepository;
import com.example.rentalManagement.tenant.services.TenantService;
import com.example.rentalManagement.user.dtos.ProfileDto;
import com.example.rentalManagement.user.dtos.UpdateProfileRequestDto;
import com.example.rentalManagement.user.dtos.UserResponseDto;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final TenantRepository tenantRepository;
  private final LandlordRepository landlordRepository;
  private final TenantMapper tenantMapper;
  private final LandlordMapper landlordMapper;
  private final LandlordService landlordService;
  private final TenantService tenantService;
  private final ObjectMapper objectMapper;

  @Override
  public UserResponseDto getMe(String email) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    ProfileDto profile =
        switch (user.getRole()) {
          case TENANT -> {
            Tenant tenant =
                tenantRepository
                    .findByUserId(user.getId())
                    .orElseThrow(TenantNotFoundException::new);
            yield ProfileDto.builder()
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
                .tin(null)
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();
          }
          case LANDLORD -> {
            Landlord landlord =
                landlordRepository
                    .findByUserId(user.getId())
                    .orElseThrow(LandlordNotFoundException::new);
            yield ProfileDto.builder()
                .id(landlord.getId())
                .name(landlord.getName())
                .surname(landlord.getSurname())
                .dateOfBirth(landlord.getDateOfBirth())
                .street(landlord.getStreet())
                .streetNumber(landlord.getStreetNumber())
                .city(landlord.getCity())
                .postalCode(landlord.getPostalCode())
                .country(landlord.getCountry())
                .phoneNumber(landlord.getPhoneNumber())
                .tin(landlord.getTin())
                .createdAt(landlord.getCreatedAt())
                .updatedAt(landlord.getUpdatedAt())
                .build();
          }
        };

    return UserResponseDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .role(user.getRole().name())
        .profile(profile)
        .build();
  }

  @Override
  public UserResponseDto updateMe(UpdateProfileRequestDto requestDto, String email) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    switch (user.getRole()) {
      case TENANT -> {
        TenantRequestDto dto =
            TenantRequestDto.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .dateOfBirth(requestDto.getDateOfBirth())
                .street(requestDto.getStreet())
                .streetNumber(requestDto.getStreetNumber())
                .city(requestDto.getCity())
                .postalCode(requestDto.getPostalCode())
                .country(requestDto.getCountry())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        tenantService.updateMyProfile(dto, email);
      }
      case LANDLORD -> {
        LandlordRequestDto dto =
            LandlordRequestDto.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .dateOfBirth(requestDto.getDateOfBirth())
                .street(requestDto.getStreet())
                .streetNumber(requestDto.getStreetNumber())
                .city(requestDto.getCity())
                .postalCode(requestDto.getPostalCode())
                .country(requestDto.getCountry())
                .tin(requestDto.getTin())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        landlordService.updateMyProfile(dto, email);
      }
    }
    return getMe(email);
  }

  @Override
  public void deleteMe(String email) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    switch (user.getRole()) {
      case TENANT -> tenantService.deleteMyProfile(email);
      case LANDLORD -> landlordService.deleteMyProfile(email);
    }
  }
}
