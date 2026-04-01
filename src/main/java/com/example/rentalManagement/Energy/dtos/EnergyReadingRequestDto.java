package com.example.rentalManagement.Energy.dtos;

import com.example.rentalManagement.Energy.entity.EnergyType;
import com.example.rentalManagement.Energy.entity.PeriodType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyReadingRequestDto {

    @NotNull(message = "Energy type is required")
    private EnergyType type;

    @NotNull(message = "Period type is required")
    private PeriodType periodType;

    @NotNull(message = "Consumption is required")
    @Positive(message = "Consumption must be positive")
    private Double consumption;

    private Integer month;

    private Integer quarter;

    @NotNull(message = "Year is required")
    private Integer year;
}
