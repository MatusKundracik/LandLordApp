package com.example.rentalManagement.exception;

public class TenantAlreadyHasApartmentException extends RuntimeException {
  public TenantAlreadyHasApartmentException(Long id) {
    super("Tenant with id " + id + " is already assigned to another apartment");
  }
}
