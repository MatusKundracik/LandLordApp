package com.example.rentalManagement.energy.mapper;

import com.example.rentalManagement.energy.dtos.EnergyReadingRequestDto;
import com.example.rentalManagement.energy.dtos.EnergyReadingResponseDto;
import com.example.rentalManagement.energy.entity.EnergyReading;
import org.springframework.stereotype.Component;

@Component
public class EnergyReadingMapper {

  public EnergyReading toEntity(EnergyReadingRequestDto energyReadingRequestDto) {
    return EnergyReading.builder()
        .type(energyReadingRequestDto.getType())
        .periodType(energyReadingRequestDto.getPeriodType())
        .consumption(energyReadingRequestDto.getConsumption())
        .month(energyReadingRequestDto.getMonth())
        .quarter(energyReadingRequestDto.getQuarter())
        .year(energyReadingRequestDto.getYear())
        .build();
  }

  public EnergyReadingResponseDto toDto(EnergyReading energyReading) {
    return EnergyReadingResponseDto.builder()
        .id(energyReading.getId())
        .apartmentId(energyReading.getApartmentId())
        .type(energyReading.getType())
        .periodType(energyReading.getPeriodType())
        .consumption(energyReading.getConsumption())
        .month(energyReading.getMonth())
        .quarter(energyReading.getQuarter())
        .year(energyReading.getYear())
        .createdAt(energyReading.getCreatedAt())
        .updatedAt(energyReading.getUpdatedAt())
        .build();
  }
}
