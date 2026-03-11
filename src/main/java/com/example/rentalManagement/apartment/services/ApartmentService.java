package com.example.rentalManagement.apartment.services;

import com.example.rentalManagement.apartment.dtos.ApartmentRequestDto;
import com.example.rentalManagement.apartment.dtos.ApartmentResponseDto;
import java.util.List;

public interface ApartmentService {

  ApartmentResponseDto createApartment(ApartmentRequestDto apartmentRequestDto);

  ApartmentResponseDto getApartmentById(long id);

  List<ApartmentResponseDto> getAllApartments(String email);

  ApartmentResponseDto updateApartment(long id, ApartmentRequestDto apartmentRequestDto);

  void deleteApartment(long id);
}
