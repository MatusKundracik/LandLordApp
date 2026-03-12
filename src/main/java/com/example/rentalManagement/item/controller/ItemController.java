package com.example.rentalManagement.item.controller;

import com.example.rentalManagement.item.dtos.ItemRequestDto;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import com.example.rentalManagement.item.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping
  public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemRequestDto requestDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(requestDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long id) {
    return ResponseEntity.ok(itemService.getItemById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ItemResponseDto> updateItem(
      @PathVariable Long id, @RequestBody ItemRequestDto requestDto) {
    return ResponseEntity.ok(itemService.updateItem(id, requestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    itemService.deleteItem(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ItemResponseDto> uploadImage(
      @PathVariable Long id,
      @RequestPart("file") MultipartFile file,
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(itemService.uploadImage(id, file, email));
  }
}
