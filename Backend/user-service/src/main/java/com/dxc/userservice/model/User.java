package com.dxc.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "_users")
public class User{
	
	@Id
	@GeneratedValue
	private Integer companyId;
	private String companyName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String pinCode;
	private String repFirstName;
	private String repLastName;
	private String email;
	private String password;
	
}
