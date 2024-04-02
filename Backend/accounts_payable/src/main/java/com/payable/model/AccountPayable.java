package com.payable.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountPayable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payableId;
    private String invoiceNumber;
    private LocalDate dueDate;
    private String vendorName;
	private String productDescription;
    private int quantity;
    private double price;
    private double totalAmount;
    private String status;
    private int companyId;   
    
}
