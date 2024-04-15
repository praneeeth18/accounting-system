package com.dxc.userservice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;
import com.dxc.userservice.model.UserDetailsDTO;
import com.dxc.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequest request) {
		return userService.addUser(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
		return userService.login(request);
	}
	
	@GetMapping("/getUserDetails/{email}")
	public ResponseEntity<UserDetailsDTO> getUserDetailsByEmail(@PathVariable String email) {
		return userService.getUserDetailsByEmail(email);
	}
	
	@GetMapping("/getDetailsByCompanyId/{id}")
	public ResponseEntity<UserDetailsDTO> getDetailsByCompanyId(@PathVariable int id) {
		return userService.getCompanyById(id);
	}
	
	@PutMapping("/forgotPassword")
	public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody LoginRequest newCredentials) {
	    return userService.forgotPassword(newCredentials);
	}

}
