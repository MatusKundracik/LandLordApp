package com.example.rentalManagement.Energy.controller;

import com.example.rentalManagement.Energy.dtos.EnergyReadingRequestDto;
import com.example.rentalManagement.Energy.dtos.EnergyReadingResponseDto;
import com.example.rentalManagement.Energy.services.EnergyReadingService;
import jakarta.validation.Valid;
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
            @PathVariable Long apartmentId,
            @Valid @RequestBody EnergyReadingRequestDto requestDto
    ) {
        return energyReadingService.createForApartment(apartmentId, requestDto);
    }
}
