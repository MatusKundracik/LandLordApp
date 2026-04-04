package com.example.rentalManagement.energy.dtos;

import com.example.rentalManagement.energy.entity.EnergyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyTypeSummaryDto {
  private EnergyType type;
  private Double totalConsumption;
}
