package com.example.rentalManagement.exception;

public class RentalAgreementNotFoundException extends RuntimeException {
  public RentalAgreementNotFoundException() {
    super("Rental agreement not found");
  }

  public RentalAgreementNotFoundException(long id) {
    super("Rental agreement with id " + id + " not found");
  }
}
