package com.example.myApp.landlord.controller;

import com.example.myApp.landlord.dtos.LandlordRequestDto;
import com.example.myApp.landlord.dtos.LandlordResponseDto;
import com.example.myApp.landlord.entity.Landlord;
import com.example.myApp.landlord.services.LandlordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landlords")
@RequiredArgsConstructor
public class LandlordController {

    private final LandlordService landlordService;

    @GetMapping
    public ResponseEntity<List<LandlordResponseDto>> getAllLandlords() {
        return ResponseEntity.ok(landlordService.getAllLandlords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandlordResponseDto> getLandlordById(@PathVariable Long id) {
        return ResponseEntity.ok(landlordService.getLandlordById(id));
    }

    @PostMapping
    public ResponseEntity<LandlordResponseDto> createLandlord(@RequestBody LandlordRequestDto dto) {
        return ResponseEntity.ok(landlordService.createLandlord(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandlordResponseDto> updateLandlord(@PathVariable Long id, @RequestBody LandlordRequestDto dto) {
        return ResponseEntity.ok(landlordService.updateLandlord(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandlord(@PathVariable Long id) {
        landlordService.deleteLandlord(id);
        return ResponseEntity.noContent().build();
    }
}


