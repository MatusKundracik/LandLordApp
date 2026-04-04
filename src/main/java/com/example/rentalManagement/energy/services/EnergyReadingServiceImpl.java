package com.example.rentalManagement.energy.services;

import com.example.rentalManagement.energy.dtos.*;
import com.example.rentalManagement.energy.entity.EnergyReading;
import com.example.rentalManagement.energy.mapper.EnergyReadingMapper;
import com.example.rentalManagement.energy.repository.EnergyReadingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnergyReadingServiceImpl implements EnergyReadingService {

  private final EnergyReadingRepository energyReadingRepository;
  private final EnergyReadingMapper energyReadingMapper;

  @Override
  public EnergyReadingResponseDto createForApartment(
      Long apartmentId, EnergyReadingRequestDto requestDto) {
    EnergyReading energyReading = energyReadingMapper.toEntity(requestDto);
    energyReading.setApartmentId(apartmentId);
    EnergyReading savedEnergyReading = energyReadingRepository.save(energyReading);
    return energyReadingMapper.toDto(savedEnergyReading);
  }

  @Override
  public List<EnergyReadingResponseDto> getAllByApartmentId(Long apartmentId) {
    return energyReadingRepository.findByApartmentId(apartmentId).stream()
        .map(energyReadingMapper::toDto)
        .toList();
  }

  @Override
  public EnergyReadingSummaryDto getSummaryByApartmentId(Long apartmentId) {
    List<EnergyTypeSummaryDto> totals =
        energyReadingRepository.findSummaryByApartmentId(apartmentId).stream()
            .map(
                projection ->
                    EnergyTypeSummaryDto.builder()
                        .type(projection.getType())
                        .totalConsumption(projection.getTotalConsumption())
                        .build())
            .toList();

    return EnergyReadingSummaryDto.builder().apartmentId(apartmentId).totals(totals).build();
  }
}
