package com.dxc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="LEDGER")
public class Ledger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int entryid;
	private String companyid;
	private Date transactiondate;
	private String description;
	private double amount;
	private double balance;
	public Ledger() {
		super();
	}
	public Ledger(int entryid, String companyid, Date transactiondate, String description, double amount,
			double balance) {
		super();
		this.entryid = entryid;
		this.companyid = companyid;
		this.transactiondate = transactiondate;
		this.description = description;
		this.amount = amount;
		this.balance = balance;
	}
	public int getEntryid() {
		return entryid;
	}
	public void setEntryid(int entryid) {
		this.entryid = entryid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public Date getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Ledger [entryid=" + entryid + ", companyid=" + companyid + ", transactiondate=" + transactiondate
				+ ", description=" + description + ", amount=" + amount + ", balance=" + balance + "]";
	}
	
	

}
