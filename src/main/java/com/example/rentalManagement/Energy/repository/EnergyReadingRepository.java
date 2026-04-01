package com.example.rentalManagement.Energy.repository;
import com.example.rentalManagement.Energy.entity.EnergyReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergyReadingRepository extends JpaRepository<EnergyReading, Long> {
    List<EnergyReading> findByApartmentId(Long apartmentId);
}
