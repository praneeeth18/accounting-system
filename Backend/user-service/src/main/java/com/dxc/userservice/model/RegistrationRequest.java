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
	
	private String companyName;
	private String address;
	private String country;
	private String state;
	private String pincode;
	private String repFirstName;
	private String repLastName;
	private String phoneNo;
	private String email;
	private String password;

}
