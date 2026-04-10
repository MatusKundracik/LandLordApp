package com.example.rentalManagement.exception;

public class RentalAgreementConflictException extends RuntimeException {
  public RentalAgreementConflictException() {
    super("A rental agreement already exists for this apartment in the given period.");
  }
}
