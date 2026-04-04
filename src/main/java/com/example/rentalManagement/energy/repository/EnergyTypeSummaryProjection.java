package com.example.rentalManagement.energy.repository;

import com.example.rentalManagement.energy.entity.EnergyType;

public interface EnergyTypeSummaryProjection {
  EnergyType getType();

  Double getTotalConsumption();
}
