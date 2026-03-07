package com.example.myApp.exception;

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException() {
    super("Item not found");
  }

  public ItemNotFoundException(long id) {
    super("Item with id " + id + " not found");
  }
}
