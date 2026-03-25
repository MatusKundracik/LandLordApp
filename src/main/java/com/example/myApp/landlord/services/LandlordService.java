package com.example.myApp.landlord.services;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
import java.util.List;

public interface LandlordService {

  List<LandlordResponseDto> getAllLandlords();

  LandlordResponseDto getLandlordById(Long id);

  LandlordResponseDto updateLandlord(Long id, LandlordRequestDto dto);

  void deleteLandlord(Long id);

  Landlord getLandlordByEmail(String email);
}
