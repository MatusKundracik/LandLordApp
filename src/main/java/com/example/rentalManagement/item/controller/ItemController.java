package com.example.rentalManagement.item.controller;

import com.example.rentalManagement.item.dtos.ItemRequestDto;
import com.example.rentalManagement.item.dtos.ItemResponseDto;
import com.example.rentalManagement.item.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

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
}
