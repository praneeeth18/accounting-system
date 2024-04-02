package com.dxc.userservice.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;
import com.dxc.userservice.model.User;
import com.dxc.userservice.model.UserDetailsDTO;
import com.dxc.userservice.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface{
	
	private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    private static final String MESSAGE = "message";
    
    public UserService(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

	
	@Override
	public ResponseEntity<Map<String, String>> addUser(RegistrationRequest request) {
		Map<String, String> response = new HashMap<>();
		try {
			
			if (userRepository.existsByEmail(request.getEmail())) {
				response.put(MESSAGE, "Email already exists!");
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
			
            String subject = "Registration Confirmation";
            String body = 
            		"Dear " + user.getRepFirstName() + ",\n\nWe are delighted to inform you that your registration"
            				+ " as an accountant for "+ user.getCompanyName() + " has been successfully completed in"
            			    + " our Accounting System Application.\n\nThank you for choosing our services."
            			    + "\n\nBest regards,\nThe Accounting System Team";
            emailSenderService.sendConfirmationEmail(user.getEmail(), subject, body);
			
            response.put(MESSAGE, "User added successfully!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "User registration unsuccessful!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> login(LoginRequest request) {
		Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
            if (optionalUser.isEmpty()) {
            	response.put(MESSAGE, "User not found!");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            User user = optionalUser.get();
            String hashedPassword = hashPassword(request.getPassword());
            if (!user.getPassword().equals(hashedPassword)) {
            	response.put(MESSAGE, "Invalid credentials!");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            response.put(MESSAGE, "Login successful!");
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put(MESSAGE, "Login failed!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

	
	@Override
	public ResponseEntity<UserDetailsDTO> getUserDetailsByEmail(String email) {
	    try {
	        Optional<User> optionalUser = userRepository.findByEmail(email);
	        if (optionalUser.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        User user = optionalUser.get();

	        UserDetailsDTO userDTO = new UserDetailsDTO();
	        userDTO.setCompanyId(user.getCompanyId());
	        userDTO.setCompanyName(user.getCompanyName());
	        userDTO.setAddress(user.getAddress());
	        userDTO.setCountry(user.getCountry());
	        userDTO.setState(user.getState());
	        userDTO.setCity(user.getCity());
	        userDTO.setPinCode(user.getPinCode());
	        userDTO.setRepFirstName(user.getRepFirstName());
	        userDTO.setRepLastName(user.getRepLastName());
	        userDTO.setEmail(user.getEmail());

	        return ResponseEntity.ok(userDTO);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	@Override
	public ResponseEntity<UserDetailsDTO> getCompanyById(int id) {
	    try {
	        Optional<User> optionalUser = userRepository.findById(id);
	        if (optionalUser.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        User company = optionalUser.get();

	        UserDetailsDTO companyDTO = new UserDetailsDTO();
	        companyDTO.setCompanyId(company.getCompanyId());
	        companyDTO.setCompanyName(company.getCompanyName());
	        companyDTO.setAddress(company.getAddress());
	        companyDTO.setCountry(company.getCountry());
	        companyDTO.setState(company.getState());
	        companyDTO.setCity(company.getCity());
	        companyDTO.setPinCode(company.getPinCode());
	        companyDTO.setRepFirstName(company.getRepFirstName());
	        companyDTO.setRepLastName(company.getRepLastName());
	        companyDTO.setEmail(company.getEmail());

	        return ResponseEntity.ok(companyDTO);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	@Override
	public ResponseEntity<Map<String, String>> forgotPassword(LoginRequest newCredentials) {
	    Map<String, String> response = new HashMap<>();
	    try {
	        Optional<User> optionalUser = userRepository.findByEmail(newCredentials.getEmail());
	        if (optionalUser.isEmpty()) {
	            response.put(MESSAGE, "User not found!");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        User user = optionalUser.get();
	        
	        // Update user's password with the new password
	        String hashedPassword = hashPassword(newCredentials.getPassword());
	        user.setPassword(hashedPassword);
	        userRepository.save(user);

	        // Send confirmation email
	        String subject = "Password Reset Confirmation";
	        String body = "Dear " + user.getRepFirstName() + ",\n\nYour password has been successfully reset for the account associated with email " +
	                user.getEmail() + ".\n\nIf you did not request this change, please contact support immediately.\n\nBest regards,\nThe Accounting System Team";
	        emailSenderService.sendConfirmationEmail(user.getEmail(), subject, body);

	        response.put(MESSAGE, "Password reset successful!");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put(MESSAGE, "Failed to reset password!");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	


}
