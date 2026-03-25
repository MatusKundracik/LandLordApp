package com.example.rentalManagement.landlord.controller;

import com.example.rentalManagement.landlord.services.LandlordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/landlords")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('LANDLORD')")
public class LandlordController {

  private final LandlordService landlordService;

  //  @GetMapping("/profile")
  //  public ResponseEntity<LandlordResponseDto> getMyProfile(@AuthenticationPrincipal String email)
  // {
  //    return ResponseEntity.ok(landlordService.getMyProfile(email));
  //  }
  //
  //  @PutMapping("/profile")
  //  public ResponseEntity<LandlordResponseDto> updateMyProfile(
  //      @Valid @RequestBody LandlordRequestDto dto, @AuthenticationPrincipal String email) {
  //    return ResponseEntity.ok(landlordService.updateMyProfile(dto, email));
  //  }
  //
  //  @DeleteMapping("/profile")
  //  public ResponseEntity<Void> deleteMyProfile(@AuthenticationPrincipal String email) {
  //    landlordService.deleteMyProfile(email);
  //    return ResponseEntity.noContent().build();
  //  }
}
