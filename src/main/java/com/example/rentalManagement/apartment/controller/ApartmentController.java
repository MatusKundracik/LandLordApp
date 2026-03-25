package com.example.rentalManagement.apartment.controller;

import com.example.rentalManagement.apartment.dtos.ApartmentRequestDto;
import com.example.rentalManagement.apartment.dtos.ApartmentResponseDto;
import com.example.rentalManagement.apartment.services.ApartmentService;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import com.example.rentalManagement.item.services.ItemService;
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
      @PathVariable long apartmentId, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(itemService.getAllItemsByApartmentForUser(apartmentId, email));
  }
}
