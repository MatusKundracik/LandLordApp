package com.example.myApp.item.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {
  private Long id;
  private String name;
  private String description;
  private Integer quantity;
  private BigDecimal value;
  private Long apartmentId;
}
