package com.example.myApp.rentalAgreement.dtos;

import com.example.myApp.rentalAgreement.entity.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalAgreementResponseDto {
    private Long id;
    private Long apartmentId;
    private Long tenantId;
    private Long landlordId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentAmount;
    private BigDecimal utilitiesDeposit;
    private BigDecimal securityDeposit;
    private Integer paymentDayOfMonth;
    private String iban;
    private BigDecimal penaltyRatePerDay;
    private LocalDate signedDate;
    private ContractStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
