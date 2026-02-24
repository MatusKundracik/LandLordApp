package com.example.myApp.apartment.controller;

import com.example.myApp.apartment.dtos.ApartmentRequestDto;
import com.example.myApp.apartment.dtos.ApartmentResponseDto;
import com.example.myApp.apartment.services.ApartmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apartments")
@RequiredArgsConstructor
public class ApartmentController {

  private final ApartmentService apartmentService;

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
  public ResponseEntity<List<ApartmentResponseDto>> getAllApartments() {
    return ResponseEntity.ok(apartmentService.getAllApartmentsByLandlord());
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
}
