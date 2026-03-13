package com.example.rentalManagement.user.services;

import com.example.rentalManagement.exception.LandlordNotFoundException;
import com.example.rentalManagement.exception.TenantNotFoundException;
import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.mapper.LandlordMapper;
import com.example.rentalManagement.landlord.repository.LandlordRepository;
import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.mapper.TenantMapper;
import com.example.rentalManagement.tenant.repository.TenantRepository;
import com.example.rentalManagement.tenant.services.TenantService;
import com.example.rentalManagement.user.dtos.UpdateProfileRequestDto;
import com.example.rentalManagement.user.dtos.UserResponseDto;
import com.example.rentalManagement.user.entity.User;
import com.example.rentalManagement.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Object profile = switch (user.getRole()) {
            case TENANT -> tenantMapper.toDto(tenantRepository.findByUserId(user.getId())
                    .orElseThrow(TenantNotFoundException::new));
            case LANDLORD -> landlordMapper.toDto(landlordRepository.findByUserId(user.getId())
                    .orElseThrow(LandlordNotFoundException::new));
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
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        switch (user.getRole()) {
            case TENANT -> {
                TenantRequestDto dto = TenantRequestDto.builder()
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
                LandlordRequestDto dto = LandlordRequestDto.builder()
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
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        switch (user.getRole()) {
            case TENANT -> tenantService.deleteMyProfile(email);
            case LANDLORD -> landlordService.deleteMyProfile(email);
        }
    }


}

