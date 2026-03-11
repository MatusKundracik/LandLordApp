package com.example.rentalManagement.shared;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  public boolean isNew() {
    return id == null;
  }

  @Override
  public int hashCode() {
    if (getId() != null) {
      return getId().hashCode();
    }
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AbstractEntity other = (AbstractEntity) obj;

    if (getId() == null || other.getId() == null) {
      return false;
    }

    return getId().equals(other.getId());
  }
}
