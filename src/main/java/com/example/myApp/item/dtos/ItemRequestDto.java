package com.example.myApp.item.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequestDto {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Description is required")
  private String description;

  @Positive
  @NotNull(message = "Quantity is required")
  private Integer quantity;

  @Positive
  @NotNull(message = "Value is required")
  private BigDecimal value;

  @NotNull(message = "Apartment is required")
  private Long apartmentId;
}
