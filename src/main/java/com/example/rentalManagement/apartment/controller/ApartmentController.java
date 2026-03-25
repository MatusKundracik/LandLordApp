package com.example.rentalManagement.apartment.controller;

import com.example.rentalManagement.apartment.dtos.ApartmentRequestDto;
import com.example.rentalManagement.apartment.dtos.ApartmentResponseDto;
import com.example.rentalManagement.apartment.services.ApartmentService;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import com.example.rentalManagement.item.services.ItemService;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementRequestDto;
import com.example.rentalManagement.rentalAgreement.dtos.RentalAgreementResponseDto;
import com.example.rentalManagement.rentalAgreement.services.RentalAgreementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apartments")
@RequiredArgsConstructor
public class ApartmentController {

  private final ApartmentService apartmentService;
  private final ItemService itemService;
  private final RentalAgreementService rentalAgreementService;

  @PostMapping
  public ResponseEntity<ApartmentResponseDto> createApartment(
      @RequestBody ApartmentRequestDto requestDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(apartmentService.createApartment(requestDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApartmentResponseDto> getApartmentById(@PathVariable Long id) {
    return ResponseEntity.ok(apartmentService.getApartmentById(id));
  }

  @GetMapping
  public ResponseEntity<List<ApartmentResponseDto>> getAllApartments(
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(apartmentService.getAllApartments(email));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApartmentResponseDto> updateApartment(
      @PathVariable Long id, @RequestBody ApartmentRequestDto requestDto) {
    return ResponseEntity.ok(apartmentService.updateApartment(id, requestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteApartment(@PathVariable Long id) {
    apartmentService.deleteApartment(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{apartmentId}/items")
  public ResponseEntity<List<ItemResponseDto>> getAllItemsByApartment(
      @PathVariable Long apartmentId, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(itemService.getAllItemsByApartmentForUser(apartmentId, email));
  }

  // ZMEŇ - odober tenantId z URL
  @PostMapping("/{apartmentId}/agreement")
  public ResponseEntity<RentalAgreementResponseDto> createAgreement(
      @PathVariable Long apartmentId,
      @RequestBody RentalAgreementRequestDto dto,
      @AuthenticationPrincipal String email) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(rentalAgreementService.createRentalAgreement(apartmentId, dto, email));
  }

  // PRIDAJ - nový endpoint pre priradenie tenanta
  @PostMapping("/{apartmentId}/tenants/{tenantId}")
  public ResponseEntity<ApartmentResponseDto> assignTenant(
      @PathVariable Long apartmentId,
      @PathVariable Long tenantId,
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(apartmentService.assignTenant(apartmentId, tenantId, email));
  }
}
