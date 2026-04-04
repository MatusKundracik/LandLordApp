package com.example.rentalManagement.energy.services;

import com.example.rentalManagement.energy.dtos.EnergyReadingRequestDto;
import com.example.rentalManagement.energy.dtos.EnergyReadingResponseDto;
import com.example.rentalManagement.energy.dtos.EnergyReadingSummaryDto;

import java.util.List;

public interface EnergyReadingService {

    EnergyReadingResponseDto createForApartment(Long apartmentId, EnergyReadingRequestDto requestDto);

    List<EnergyReadingResponseDto> getAllByApartmentId(Long apartmentId);

    EnergyReadingSummaryDto getSummaryByApartmentId(Long apartmentId);
}
