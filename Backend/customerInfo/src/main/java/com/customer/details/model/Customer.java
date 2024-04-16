package com.customer.details.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customerdetails")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int customerId;
  private String customerName;

  @NotBlank(message = "Email cannot be blank")
  @Pattern(regexp = "^[\\p{L}0-9._%+-]+@[\\p{L}0-9.-]+\\.[\\p{L}]{2,}$", message = "Email should be valid")
  private String customerEmail;
  private int companyId;
}
