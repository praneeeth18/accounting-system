package com.dxc.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationRequest {
	
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String password;
	private String role;
	private String companyName;
	private String address;
	private String country;
	private String state;
	private String pincode;

}
