package com.payable.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="acc_payable")
public class AccountPayable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payableId;
    private String invoiceNumber;
    private LocalDate date;
    private String vendorName;
	private String productDescription;
    private int quantity;
    private double price;
    private double totalAmount;
    private String status;
    private int companyId;
    
    public Long getPayableId() {
        return payableId;
    }
    public void setPayableId(Long payableId) {
        this.payableId = payableId;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	@Override
    public String toString() {
        return "AccountPayable [payableId=" + payableId + ", invoiceNumber=" + invoiceNumber + ", date=" + date + ", vendorName=" + vendorName
                + ", productDescription=" + productDescription + ", quantity=" + quantity + ", price=" + price
                + ", totalAmount=" + totalAmount + ", status=" + status + ", companyId=" + companyId + "]";
    }   
}
