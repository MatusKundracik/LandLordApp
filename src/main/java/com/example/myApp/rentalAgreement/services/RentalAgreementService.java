package com.example.myApp.rentalAgreement.services;

import com.example.myApp.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.myApp.rentalAgreement.dtos.RentalAgreementResponseDto;
import java.util.List;

public interface RentalAgreementService {

  RentalAgreementResponseDto createRentalAgreement(
      RentalAgreementRequestDto requestDto, String email);

  RentalAgreementResponseDto updateRentalAgreement(
      long id, RentalAgreementRequestDto requestDto, String email);

  RentalAgreementResponseDto getRentalAgreement(long id, String email);

  List<RentalAgreementResponseDto> getRentalAgreementsByLandlord(String email);

  void deleteRentalAgreement(long id, String email);

  List<RentalAgreementResponseDto> getRentalAgreementsByTenant(String email);
}
