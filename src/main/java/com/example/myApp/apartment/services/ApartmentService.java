package com.example.myApp.apartment.services;

import com.example.myApp.apartment.dtos.ApartmentRequestDto;
import com.example.myApp.apartment.dtos.ApartmentResponseDto;
import java.util.List;

public interface ApartmentService {

  ApartmentResponseDto createApartment(ApartmentRequestDto apartmentRequestDto);

  ApartmentResponseDto getApartmentById(long id);

  List<ApartmentResponseDto> getAllApartmentsByLandlord();

  ApartmentResponseDto updateApartment(long id, ApartmentRequestDto apartmentRequestDto);

  void deleteApartment(long id);

}
