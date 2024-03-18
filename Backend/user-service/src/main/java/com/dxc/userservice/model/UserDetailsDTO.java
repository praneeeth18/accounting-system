package com.dxc.userservice.model;

import lombok.Data;

@Data
public class UserDetailsDTO {

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
}
