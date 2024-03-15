package com.dxc.userservice.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;
import com.dxc.userservice.model.User;
import com.dxc.userservice.repository.UserRepository;


@Service
public class UserService implements UserManagementServiceInterface {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Override
	public ResponseEntity<Map<String, String>> addUser(RegistrationRequest request) {
		Map<String, String> response = new HashMap<>();
		try {
			
			if (userRepository.existsByEmail(request.getEmail())) {
				response.put("error", "Email already exists!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
			
			String password = request.getPassword();
            String hashedPassword = hashPassword(password);
            
			var user = User.builder()
					.companyName(request.getCompanyName())
					.address(request.getAddress())
					.country(request.getCountry())
					.state(request.getState())
					.city(request.getCity())
					.pinCode(request.getPincode())
					.repFirstName(request.getRepFirstName())
					.repLastName(request.getRepLastName())
					.email(request.getEmail())
					.password(hashedPassword)
					.build();
			userRepository.save(user);
			
			// Send confirmation email
            String subject = "Registration Confirmation";
            String body = 
            		"Dear " + user.getRepFirstName() + ",\n\nWe are delighted to inform you that your registration"
            				+ " as an accountant for "+ user.getCompanyName() + " has been successfully completed in"
            			    + " our Accounting System Application.\n\nThank you for choosing our services."
            			    + "\n\nBest regards,\nThe Accounting System Team";
            emailSenderService.sendConfirmationEmail(user.getEmail(), subject, body);
            
            response.put("message", "Candidate added successfully!");
			
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "User registration unsuccessful!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<Map<String, String>> login(LoginRequest request) {
		Map<String, String> response = new HashMap<>();
        try {
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isEmpty()) {
            	response.put("error", "User not found!");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            User user = optionalUser.get();
            String hashedPassword = hashPassword(request.getPassword());
            if (!user.getPassword().equals(hashedPassword)) {
            	response.put("error", "Invalid credentials!");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            response.put("message", "Login successful!");
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Login failed!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

}
