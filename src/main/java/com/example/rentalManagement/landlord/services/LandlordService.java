package com.example.rentalManagement.landlord.services;

import com.example.rentalManagement.landlord.dtos.LandlordRequestDto;
import com.example.rentalManagement.landlord.dtos.LandlordResponseDto;
import com.example.rentalManagement.landlord.entity.Landlord;

public interface LandlordService {

  LandlordResponseDto getMyProfile(String email);

  LandlordResponseDto updateMyProfile(LandlordRequestDto dto, String email);

  void deleteMyProfile(String email);

  Landlord getLandlordByEmail(String email); // ← táto ostáva, používa sa interne
}
