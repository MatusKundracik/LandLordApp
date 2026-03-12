package com.example.rentalManagement.auth.dto;

import com.example.rentalManagement.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
  private String token;
  private User user;
}
