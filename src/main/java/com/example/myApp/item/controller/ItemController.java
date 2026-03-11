package com.example.myApp.item.controller;

import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import com.example.myApp.item.services.ItemService;
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
  public ResponseEntity<ItemResponseDto> getItemById(@PathVariable long id) {
    return ResponseEntity.ok(itemService.getItemById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ItemResponseDto> updateItem(
      @PathVariable long id, @RequestBody ItemRequestDto requestDto) {
    return ResponseEntity.ok(itemService.updateItem(id, requestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable long id) {
    itemService.deleteItem(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ItemResponseDto> uploadImage(
      @PathVariable long id,
      @RequestPart("file") MultipartFile file,
      @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(itemService.uploadImage(id, file, email));
  }
}
