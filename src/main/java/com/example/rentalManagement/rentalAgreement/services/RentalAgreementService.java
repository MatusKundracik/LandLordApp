package com.example.rentalManagement.rentalAgreement.services;

import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementResponseDto;
import java.util.List;

public interface RentalAgreementService {

  RentalAgreementResponseDto createRentalAgreement(
      RentalAgreementRequestDto requestDto, String email);

  RentalAgreementResponseDto updateRentalAgreement(
      long id, RentalAgreementRequestDto requestDto, String email);

  RentalAgreementResponseDto getRentalAgreement(long id, String email);

  void deleteRentalAgreement(long id, String email);

  List<RentalAgreementResponseDto> getRentalAgreements(String email);
}
