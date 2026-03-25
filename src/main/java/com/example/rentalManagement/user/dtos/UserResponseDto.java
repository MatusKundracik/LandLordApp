package com.example.rentalManagement.user.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto{
    private Long id;
    private String email;
    private String role;
    private ProfileDto profile;
}

