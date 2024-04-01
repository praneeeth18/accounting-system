package com.vendor.details.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendordetails")
public class Vendor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vendorId")
  private int vendorId;

  private String vendorName;
  private String vendorEmail;

  // Constructor with default values
  public Vendor() {
    this.vendorName = "";
    this.vendorEmail = "";
  }

  public int getVendorId() {
    return vendorId;
  }

  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getVendorEmail() {
    return vendorEmail;
  }

  public void setVendorEmail(String vendorEmail) {
    this.vendorEmail = vendorEmail;
  }

  @Override
  public String toString() {
    return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorEmail=" + vendorEmail + "]";
  }
}
