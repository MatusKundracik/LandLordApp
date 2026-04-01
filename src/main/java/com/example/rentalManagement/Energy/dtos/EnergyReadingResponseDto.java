package com.example.rentalManagement.Energy.dtos;

import com.example.rentalManagement.Energy.entity.EnergyType;
import com.example.rentalManagement.Energy.entity.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyReadingResponseDto {

    private Long id;
    private Long apartmentId;
    private EnergyType type;
    private PeriodType periodType;
    private Double consumption;
    private Integer month;
    private Integer quarter;
    private Integer year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}