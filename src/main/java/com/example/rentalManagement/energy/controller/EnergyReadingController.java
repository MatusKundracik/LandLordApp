package com.example.rentalManagement.energy.controller;

import com.example.rentalManagement.energy.dtos.EnergyReadingRequestDto;
import com.example.rentalManagement.energy.dtos.EnergyReadingResponseDto;
import com.example.rentalManagement.energy.dtos.EnergyReadingSummaryDto;
import com.example.rentalManagement.energy.services.EnergyReadingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apartments/{apartmentId}/energy-readings")
@RequiredArgsConstructor
public class EnergyReadingController {
  private final EnergyReadingService energyReadingService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EnergyReadingResponseDto createEnergyReading(
      @PathVariable Long apartmentId, @Valid @RequestBody EnergyReadingRequestDto requestDto) {
    return energyReadingService.createForApartment(apartmentId, requestDto);
  }

  @GetMapping
  public List<EnergyReadingResponseDto> getAllEnergyReadings(@PathVariable Long apartmentId) {
    return energyReadingService.getAllByApartmentId(apartmentId);
  }

  @GetMapping("/summary")
  public EnergyReadingSummaryDto getEnergyReadingSummary(@PathVariable Long apartmentId) {
    return energyReadingService.getSummaryByApartmentId(apartmentId);
  }
}
