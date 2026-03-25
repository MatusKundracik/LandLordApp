package com.example.rentalManagement.landlord.entity;

import com.example.rentalManagement.shared.AuditableEntity;
import com.example.rentalManagement.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "landlords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Landlord extends AuditableEntity {

  private String name;
  private String surname;
  private LocalDate dateOfBirth;
  private String street;
  private String streetNumber;
  private String city;
  private String postalCode;
  private String country;
  private String tin;
  private String phoneNumber;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;
}
