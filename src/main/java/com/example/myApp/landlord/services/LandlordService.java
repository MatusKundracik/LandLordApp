package com.example.myApp.landlord.services;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;

public interface LandlordService {

  LandlordResponseDto getMyProfile(String email);

  LandlordResponseDto updateMyProfile(LandlordRequestDto dto, String email);

  void deleteMyProfile(String email);

  Landlord getLandlordByEmail(String email); // ← táto ostáva, používa sa interne
}
