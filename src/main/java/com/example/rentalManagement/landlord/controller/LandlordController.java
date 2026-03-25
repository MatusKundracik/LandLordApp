package com.example.rentalManagement.landlord.controller;

import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/landlords")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('LANDLORD')")
public class LandlordController {

  private final LandlordService landlordService;

  @PostMapping("/tenants")
  public ResponseEntity<TenantResponseDto> createTenant(
      @Valid @RequestBody TenantRequestDto tenantRequestDto) {
    return ResponseEntity.ok(landlordService.createTenant(tenantRequestDto));
  }

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
