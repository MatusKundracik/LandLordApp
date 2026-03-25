package com.example.rentalManagement.rentalAgreement.dtos;

import com.example.rentalManagement.rentalAgreement.entity.ContractStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private BigDecimal penaltyRatePerDay;
  private LocalDate signedDate;
  private ContractStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
