package com.dxc.userservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationRequest {
	
	@NotBlank(message = "Company name cannot be blank")
    private String companyName;
    
    @NotBlank(message = "Address cannot be blank")
    private String address;
    
    @NotBlank(message = "Country cannot be blank")
    private String country;
    
    @NotBlank(message = "State cannot be blank")
    private String state;
    
    @NotBlank(message = "City cannot be blank")
    private String city;
    
    @NotBlank(message = "Pin code cannot be blank")
    @Pattern(regexp = "\\d{6}", message = "Pin code must be a 6-digit number")
    private String pincode;
    
    @NotBlank(message = "Representative first name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Representative first name should contain only alphabets")
    private String repFirstName;
    
    @NotBlank(message = "Representative last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Representative last name should contain only alphabets")
    private String repLastName;
    
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String phoneNo;
    
    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password cannot be blank")
    private String password;

}
