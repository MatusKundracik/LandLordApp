package com.example.rentalManagement.Energy.entity;

import com.example.rentalManagement.shared.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "energy_readings")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyReading extends AuditableEntity {

    @Column(name = "apartment_id", nullable = false)
    private Long apartmentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnergyType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "period_type", nullable = false, length = 20)
    private PeriodType periodType;

    @Column(nullable = false)
    private Double consumption;

    private Integer month;

    private Integer quarter;

    @Column(nullable = false)
    private Integer year;
    
}