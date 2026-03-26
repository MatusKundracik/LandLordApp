package com.example.rentalManagement.tenant.controller;

import com.example.rentalManagement.landlord.services.LandlordService;
import com.example.rentalManagement.tenant.dtos.TenantRequestDto;
import com.example.rentalManagement.tenant.dtos.TenantResponseDto;
import com.example.rentalManagement.tenant.services.TenantService;
import java.util.List;
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
  private final LandlordService landlordService;

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteTenant(
      @AuthenticationPrincipal String email, @PathVariable Long id) {
    landlordService.deleteTenant(email, id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("{id}")
  public ResponseEntity<TenantResponseDto> updateTenant(
      @RequestBody TenantRequestDto tenantRequestDto,
      @AuthenticationPrincipal String email,
      @PathVariable Long id) {
    return ResponseEntity.ok(landlordService.updateTenant(tenantRequestDto, id, email));
  }

  @GetMapping("search")
  public ResponseEntity<List<TenantResponseDto>> searchTenants(
      @RequestParam String query, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(landlordService.searchTenants(query, email));
  }

  @GetMapping("/all")
  public ResponseEntity<List<TenantResponseDto>> getAllTenantsByApartmentId(
      Long apartmentId, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(tenantService.getAllTenantsByApartmentId(apartmentId, email));
  }
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
