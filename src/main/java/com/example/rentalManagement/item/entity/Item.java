package com.example.rentalManagement.item.entity;

import com.example.rentalManagement.apartment.entity.Apartment;
import com.example.rentalManagement.shared.AuditableEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends AuditableEntity {

  private String name;
  private String description;
  private Integer quantity;
  private BigDecimal value; // Hodnota
  private String imageUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
}
