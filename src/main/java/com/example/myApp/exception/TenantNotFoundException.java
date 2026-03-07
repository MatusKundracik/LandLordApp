package com.example.myApp.exception;

public class TenantNotFoundException extends RuntimeException {
  public TenantNotFoundException() {
    super("Tenant not found");
  }

  public TenantNotFoundException(long id) {
    super("Tenant with id " + id + " not found");
  }
}
