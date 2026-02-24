package com.example.myApp.apartment.mapper;

import com.example.myApp.apartment.dtos.ApartmentRequestDto;
import com.example.myApp.apartment.dtos.ApartmentResponseDto;
import com.example.myApp.apartment.entity.Apartment;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper {

  public Apartment toEntity(ApartmentRequestDto apartmentRequestDto) {
    return Apartment.builder()
        .street(apartmentRequestDto.getStreet())
        .streetNumber(apartmentRequestDto.getStreetNumber())
        .city(apartmentRequestDto.getCity())
        .postalCode(apartmentRequestDto.getPostalCode())
        .apartmentNumber(apartmentRequestDto.getApartmentNumber())
        .floor(apartmentRequestDto.getFloor())
        .areaSqm(apartmentRequestDto.getAreaSqm())
        .buildingRegNumber(apartmentRequestDto.getBuildingRegNumber())
        .cadastralArea(apartmentRequestDto.getCadastralArea())
        .titleDeedNumber(apartmentRequestDto.getTitleDeedNumber())
        .unitType(apartmentRequestDto.getUnitType())
        .build();
  }

  public ApartmentResponseDto toDto(Apartment apartment) {
    return ApartmentResponseDto.builder()
        .id(apartment.getId())
        .street(apartment.getStreet())
        .streetNumber(apartment.getStreetNumber())
        .city(apartment.getCity())
        .postalCode(apartment.getPostalCode())
        .apartmentNumber(apartment.getApartmentNumber())
        .floor(apartment.getFloor())
        .areaSqm(apartment.getAreaSqm())
        .buildingRegNumber(apartment.getBuildingRegNumber())
        .cadastralArea(apartment.getCadastralArea())
        .titleDeedNumber(apartment.getTitleDeedNumber())
        .unitType(apartment.getUnitType())
        .landlordId(apartment.getLandlord().getId())
        .build();
  }
}
