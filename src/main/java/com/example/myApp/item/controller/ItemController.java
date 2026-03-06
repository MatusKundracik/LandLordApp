package com.example.myApp.item.controller;

import com.example.myApp.item.dtos.ItemRequestDto;
import com.example.myApp.item.dtos.ItemResponseDto;
import com.example.myApp.item.services.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/apartment/{apartmentId}")
  public ResponseEntity<List<ItemResponseDto>> getAllItemsByApartment(
      @PathVariable long apartmentId) {
    return ResponseEntity.ok(itemService.getAllItemsByApartment(apartmentId));
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

  @GetMapping("/apartment/{apartmentId}/my-items")
  public ResponseEntity<List<ItemResponseDto>> getAllItemsForTenantByApartment(
      @PathVariable long apartmentId, @AuthenticationPrincipal String email) {
    return ResponseEntity.ok(itemService.getAllItemsForTenantByApartment(apartmentId, email));
  }
}
