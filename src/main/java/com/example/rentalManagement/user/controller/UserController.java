package com.example.rentalManagement.user.controller;

import com.example.rentalManagement.user.dtos.UpdateProfileRequestDto;
import com.example.rentalManagement.user.dtos.UserResponseDto;
import com.example.rentalManagement.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserResponseDto> getMe(@AuthenticationPrincipal String email) {
    return ResponseEntity.ok(userService.getMe(email));
  }

  @PatchMapping("/me")
  public ResponseEntity<UserResponseDto> updateMe(
      @RequestBody UpdateProfileRequestDto requestDto, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(userService.updateMe(requestDto, email));
  }

  @DeleteMapping("/me")
  public ResponseEntity<Void> deleteMe(@AuthenticationPrincipal String email) {
    userService.deleteMe(email);
    return ResponseEntity.noContent().build();
  }
}
