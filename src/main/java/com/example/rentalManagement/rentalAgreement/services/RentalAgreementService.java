package com.example.rentalManagement.rentalAgreement.services;

import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementResponseDto;
import java.util.List;

public interface RentalAgreementService {

  RentalAgreementResponseDto createRentalAgreement(
      Long apartmentId, RentalAgreementRequestDto dto, String email);

  RentalAgreementResponseDto updateRentalAgreement(
      Long id, RentalAgreementRequestDto requestDto, String email);

  RentalAgreementResponseDto getRentalAgreement(Long id, String email);

  void deleteRentalAgreement(Long id, String email);

  List<RentalAgreementResponseDto> getRentalAgreements(String email);
}
