package com.example.rentalManagement.exception;

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException() {
    super("Item not found");
  }

  public ItemNotFoundException(Long id) {
    super("Item with id " + id + " not found");
  }
}
