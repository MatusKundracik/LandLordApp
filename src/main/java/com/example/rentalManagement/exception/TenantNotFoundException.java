package com.example.rentalManagement.exception;

public class TenantNotFoundException extends RuntimeException {
  public TenantNotFoundException() {
    super("Tenant not found");
  }

  public TenantNotFoundException(Long id) {
    super("Tenant with id " + id + " not found");
  }
}
