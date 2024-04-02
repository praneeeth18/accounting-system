package com.dxc.userservice.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;
import com.dxc.userservice.model.UserDetailsDTO;

public interface UserServiceInterface {

	public ResponseEntity<Map<String, String>> addUser(RegistrationRequest request);
	public ResponseEntity<Map<String, Object>> login(LoginRequest request);
	public ResponseEntity<UserDetailsDTO> getUserDetailsByEmail(String email);
	public ResponseEntity<UserDetailsDTO> getCompanyById(int id);
	public ResponseEntity<Map<String, String>> forgotPassword(LoginRequest newCredentials);
}
