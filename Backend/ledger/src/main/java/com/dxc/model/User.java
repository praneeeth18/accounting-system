package com.dxc.model;

import lombok.Data;

@Data
public class User{
	
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
