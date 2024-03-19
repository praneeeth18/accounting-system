package com.payable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payable")
public class AccountPayable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payableId;
    private String invoiceNumber;
    private String customerName;
    private String productDescription;
    private int quantity;
    private double price;
    private double totalAmount;
    private String status;
	public Long getpayableId() {
		return payableId;
	}
	public void setpayableId(Long payableId) {
		this.payableId = payableId;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	
	@Override
	public String toString() {
		return "AccountPayable [payableId=" + payableId + ", invoiceNumber=" + invoiceNumber + ", customerName=" + customerName
				+ ", productDescription=" + productDescription + ", quantity=" + quantity + ", price=" + price
				+ ", totalAmount=" + totalAmount + ", status=" + status + "]";
	}   

}
