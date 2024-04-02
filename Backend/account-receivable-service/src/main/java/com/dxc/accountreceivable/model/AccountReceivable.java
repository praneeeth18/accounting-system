package com.dxc.accountreceivable.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountReceivable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receivableId;
    private String invoiceNumber;
    private String productDescription;
    private int quantity;
    private double price;
    private LocalDate dueDate;
    private double amount;
    private String status;

    private String customerName;
    private int companyId;
    
}