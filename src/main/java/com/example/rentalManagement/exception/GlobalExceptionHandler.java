package com.example.rentalManagement.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({
    TenantNotFoundException.class,
    LandlordNotFoundException.class,
    ApartmentNotFoundException.class,
    RentalAgreementNotFoundException.class,
    ItemNotFoundException.class,
    UserNotFoundException.class,
  })
  public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponse(403, ex.getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(500, ex.getMessage(), LocalDateTime.now()));
  }

  @ExceptionHandler({
    TenantAlreadyHasApartmentException.class,
    RentalAgreementConflictException.class
  })
  public ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorResponse(409, ex.getMessage(), LocalDateTime.now()));
  }
}
