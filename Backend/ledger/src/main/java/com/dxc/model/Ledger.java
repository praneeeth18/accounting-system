package com.dxc.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="LEDGER")
public class Ledger{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int entryid;
	private Integer companyId;
	private LocalDate transactiondate;
	private String description;
	private String transactiontype;
	private double amount;
	private double balance;

}
