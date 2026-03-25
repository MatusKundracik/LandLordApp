package com.example.rentalManagement.user.services;

import com.example.rentalManagement.user.dtos.UpdateProfileRequestDto;
import com.example.rentalManagement.user.dtos.UserResponseDto;

public interface UserService {
  UserResponseDto getMe(String email);

  UserResponseDto updateMe(UpdateProfileRequestDto requestDto, String email);

  void deleteMe(String email);
}
