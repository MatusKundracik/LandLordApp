package com.example.rentalManagement.Energy.services;

import com.example.rentalManagement.Energy.dtos.EnergyReadingRequestDto;
import com.example.rentalManagement.Energy.dtos.EnergyReadingResponseDto;
import com.example.rentalManagement.Energy.entity.EnergyReading;
import com.example.rentalManagement.Energy.mapper.EnergyReadingMapper;
import com.example.rentalManagement.Energy.repository.EnergyReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnergyReadingService {

    private final EnergyReadingRepository energyReadingRepository;
    private final EnergyReadingMapper energyReadingMapper;

    public EnergyReadingResponseDto createForApartment(Long apartmentId, EnergyReadingRequestDto requestDto) {
        EnergyReading energyReading = energyReadingMapper.toEntity(requestDto);
        energyReading.setApartmentId(apartmentId);

        EnergyReading savedEnergyReading = energyReadingRepository.save(energyReading);
        return energyReadingMapper.toDto(savedEnergyReading);
    }
}