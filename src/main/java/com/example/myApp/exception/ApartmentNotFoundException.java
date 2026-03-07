package com.example.myApp.exception;

public class ApartmentNotFoundException extends RuntimeException {
  public ApartmentNotFoundException() {
    super("Apartment not found");
  }

  public ApartmentNotFoundException(long id) {
    super("Apartment with id " + id + " not found");
  }
}
