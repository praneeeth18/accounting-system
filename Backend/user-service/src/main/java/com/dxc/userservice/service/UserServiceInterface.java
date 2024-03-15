package com.dxc.userservice.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;

public interface UserServiceInterface {

	ResponseEntity<Map<String, String>> addUser(RegistrationRequest request);
	ResponseEntity<Map<String, String>> login(LoginRequest request);
}
