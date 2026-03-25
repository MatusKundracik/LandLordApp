package com.example.rentalManagement.auth.dto;

import com.example.rentalManagement.user.dtos.UserResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
  private String token;
  private UserResponseDto userResponseDto;
}
