package com.example.myApp.exception;

public class AccessDeniedException extends RuntimeException {
  public AccessDeniedException() {
    super("Access denied");
  }
}
