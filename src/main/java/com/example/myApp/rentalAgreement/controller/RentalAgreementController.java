package com.example.myApp.rentalAgreement.controller;

import com.example.myApp.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.myApp.rentalAgreement.dtos.RentalAgreementResponseDto;
import com.example.myApp.rentalAgreement.services.RentalAgreementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentalAgreement")
@RequiredArgsConstructor
public class RentalAgreementController {

  private final RentalAgreementService rentalAgreementService;

  @PostMapping
  public ResponseEntity<RentalAgreementResponseDto> createRentalAgreement(
      @RequestBody RentalAgreementRequestDto requestDto, @AuthenticationPrincipal String email) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(rentalAgreementService.createRentalAgreement(requestDto, email));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<RentalAgreementResponseDto> updateRentalAgreement(
      @PathVariable long id,
      @RequestBody RentalAgreementRequestDto requestDto,
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(rentalAgreementService.updateRentalAgreement(id, requestDto, email));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalAgreementResponseDto> getRentalAgreement(
      @PathVariable long id, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(rentalAgreementService.getRentalAgreement(id, email));
  }

  @GetMapping
  public ResponseEntity<List<RentalAgreementResponseDto>> getRentalAgreements(
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(rentalAgreementService.getRentalAgreementsByLandlord(email));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRentalAgreement(
      @PathVariable long id, @AuthenticationPrincipal String email) {
    rentalAgreementService.deleteRentalAgreement(id, email);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/my-agreements")
  public ResponseEntity<List<RentalAgreementResponseDto>> getMyAgreements(
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(rentalAgreementService.getRentalAgreementsByTenant(email));
  }
}
