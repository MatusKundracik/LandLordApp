package com.example.rentalManagement.exception;

public class LandlordNotFoundException extends RuntimeException {
  public LandlordNotFoundException() {
    super("Landlord not found");
  }

  public LandlordNotFoundException(Long id) {
    super("Landlord with id " + id + " not found");
  }
}
