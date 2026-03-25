package com.example.rentalManagement.landlord.mapper;

import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.dtos.LandlordResponseDto;
import com.example.rentalManagement.landlord.entity.Landlord;
import org.springframework.stereotype.Component;

@Component
public class LandlordMapper {

  public LandlordResponseDto toDto(Landlord landlord) {
    return LandlordResponseDto.builder()
        .id(landlord.getId())
        .name(landlord.getName())
        .surname(landlord.getSurname())
        .dateOfBirth(landlord.getDateOfBirth())
        .street(landlord.getStreet())
        .streetNumber(landlord.getStreetNumber())
        .city(landlord.getCity())
        .postalCode(landlord.getPostalCode())
        .country(landlord.getCountry())
        .tin(landlord.getTin())
        .phoneNumber(landlord.getPhoneNumber())
        .email(landlord.getUser() != null ? landlord.getUser().getEmail() : null)
        .iban(landlord.getIban())
        .build();
  }

  public Landlord toEntity(LandlordRequestDto dto) {
    return Landlord.builder()
        .name(dto.getName())
        .surname(dto.getSurname())
        .dateOfBirth(dto.getDateOfBirth())
        .street(dto.getStreet())
        .streetNumber(dto.getStreetNumber())
        .city(dto.getCity())
        .postalCode(dto.getPostalCode())
        .country(dto.getCountry())
        .tin(dto.getTin())
        .phoneNumber(dto.getPhoneNumber())
        .iban(dto.getIban())
        .build();
  }
}
