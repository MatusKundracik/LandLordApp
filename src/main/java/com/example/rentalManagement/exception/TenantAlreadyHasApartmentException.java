package com.example.rentalManagement.exception;

public class TenantAlreadyHasApartmentException extends RuntimeException {
  public TenantAlreadyHasApartmentException(String message) {
    super("Tenant has already an apartment");
  }
}
