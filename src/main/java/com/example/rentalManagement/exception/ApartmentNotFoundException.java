package com.example.rentalManagement.exception;

public class ApartmentNotFoundException extends RuntimeException {
  public ApartmentNotFoundException() {
    super("Apartment not found");
  }

  public ApartmentNotFoundException(long id) {
    super("Apartment with id " + id + " not found");
  }
}
