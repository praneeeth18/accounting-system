package com.dxc.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;
import com.dxc.userservice.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequest request) {
		return userService.addUser(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		return userService.login(request);
	}
	
	@GetMapping("/getUserDetails/{email}")
	public ResponseEntity<?> getUserDetailsByEmail(@PathVariable String email) {
		return userService.getUserDetailsByEmail(email);
	}

}
