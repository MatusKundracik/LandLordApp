package com.example.rentalManagement.energy.repository;
import com.example.rentalManagement.energy.entity.EnergyReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnergyReadingRepository extends JpaRepository<EnergyReading, Long> {
    List<EnergyReading> findByApartmentId(Long apartmentId);


    @Query("SELECT e.type AS type, SUM(e.consumption) AS totalConsumption " +
            "FROM EnergyReading e " +
            "WHERE e.apartmentId = :apartmentId " +
            "GROUP BY e.type")
    List<EnergyTypeSummaryProjection> findSummaryByApartmentId(@Param("apartmentId") Long apartmentId);
}
