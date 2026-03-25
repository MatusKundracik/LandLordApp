package com.example.myApp.landlord.mapper;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
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
                .city(landlord.getCity())
                .postalCode(landlord.getPostalCode())
                .country(landlord.getCountry())
                .tin(landlord.getTin())
                .build();
    }

    public Landlord toEntity(LandlordRequestDto dto) {
        return Landlord.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .dateOfBirth(dto.getDateOfBirth())
                .street(dto.getStreet())
                .city(dto.getCity())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .tin(dto.getTin())
                .build();
    }
}
