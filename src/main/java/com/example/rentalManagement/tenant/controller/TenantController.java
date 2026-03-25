package com.example.rentalManagement.tenant.controller;

import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import com.example.rentalManagement.tenant.services.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('TENANT')")
public class TenantController {

  private final TenantService tenantService;

//  @GetMapping("/profile")
//  public ResponseEntity<TenantResponseDto> getMyProfile(@AuthenticationPrincipal String email) {
//
//    TenantResponseDto responseDto = tenantService.getMyProfile(email);
//    return ResponseEntity.ok(responseDto);
//  }
//
//  @PutMapping("/profile")
//  public ResponseEntity<TenantResponseDto> updateMyProfile(
//      @Valid @RequestBody TenantRequestDto requestDto, @AuthenticationPrincipal String email) {
//
//    TenantResponseDto responseDto = tenantService.updateMyProfile(requestDto, email);
//    return ResponseEntity.ok(responseDto);
//  }
//
//  @DeleteMapping("/profile")
//  public ResponseEntity<Void> deleteMyProfile(@AuthenticationPrincipal String email) {
//
//    tenantService.deleteMyProfile(email);
//    return ResponseEntity.noContent().build();
//  }
}
