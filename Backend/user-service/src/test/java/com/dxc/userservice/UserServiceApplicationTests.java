package com.dxc.userservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dxc.userservice.model.LoginRequest;
import com.dxc.userservice.model.RegistrationRequest;
import com.dxc.userservice.model.User;
import com.dxc.userservice.repository.UserRepository;
import com.dxc.userservice.service.EmailSenderService;
import com.dxc.userservice.service.UserService;

@SpringBootTest
class UserServiceApplicationTests {
	
	@MockBean
    private UserRepository userRepository;

    @MockBean
    private EmailSenderService emailSenderService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, emailSenderService);
    }

    @Test
    void testAddUser_Success() throws NoSuchAlgorithmException {
        RegistrationRequest request = createRegistrationRequest();
        User user = createUser();
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<Map<String, String>> responseEntity = userService.addUser(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User added successfully!", responseEntity.getBody().get("message"));
    }

    @Test
    void testAddUser_EmailExists() {
        RegistrationRequest request = createRegistrationRequest();
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        ResponseEntity<Map<String, String>> responseEntity = userService.addUser(request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Email already exists!", responseEntity.getBody().get("message"));
    }
    
    @Test
    void testLogin_Success() throws NoSuchAlgorithmException {
  
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("testPassword");

        User user = new User();
        user.setEmail("test@example.com");
        String hashedPassword = hashPassword("testPassword");
        user.setPassword(hashedPassword);

        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByEmail(request.getEmail())).thenReturn(optionalUser);

        ResponseEntity<Map<String, Object>> responseEntity = userService.login(request);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login successful!", responseEntity.getBody().get("message"));
        assertEquals(user, responseEntity.getBody().get("user"));
    }


    @Test
    void testLogin_UserNotFound() {
        LoginRequest request = createLoginRequest();
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(optionalUser);

        ResponseEntity<Map<String, Object>> responseEntity = userService.login(request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found!", responseEntity.getBody().get("message"));
    }

    @Test
    void testLogin_InvalidCredentials() {
        LoginRequest request = createLoginRequest();
        User user = createUser();
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByEmail(request.getEmail())).thenReturn(optionalUser);

        ResponseEntity<Map<String, Object>> responseEntity = userService.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials!", responseEntity.getBody().get("message"));
    }

    @Test
    void testForgotPassword_Success() throws NoSuchAlgorithmException {
        LoginRequest request = createLoginRequest();
        User user = createUser();
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByEmail(request.getEmail())).thenReturn(optionalUser);
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<Map<String, String>> responseEntity = userService.forgotPassword(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Password reset successful!", responseEntity.getBody().get("message"));
    }

    @Test
    void testForgotPassword_UserNotFound() {
        LoginRequest request = createLoginRequest();
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(optionalUser);

        ResponseEntity<Map<String, String>> responseEntity = userService.forgotPassword(request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found!", responseEntity.getBody().get("message"));
    }

    private RegistrationRequest createRegistrationRequest() {
        RegistrationRequest request = new RegistrationRequest();
        request.setCompanyName("Test Company");
        request.setAddress("123 Test Street");
        request.setCountry("Test Country");
        request.setState("Test State");
        request.setCity("Test City");
        request.setPincode("123456");
        request.setRepFirstName("John");
        request.setRepLastName("Doe");
        request.setEmail("test@example.com");
        request.setPassword("testPassword");
        return request;
    }

    private User createUser() {
        User user = new User();
        Random random = new Random();
        user.setCompanyId(random.nextInt(1000));
        user.setCompanyName("Test Company");
        user.setAddress("123 Test Street");
        user.setCountry("Test Country");
        user.setState("Test State");
        user.setCity("Test City");
        user.setPinCode("123456");
        user.setRepFirstName("John");
        user.setRepLastName("Doe");
        user.setEmail("test@example.com");
        user.setPassword("testPassword");
        return user;
    }

    private LoginRequest createLoginRequest() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("testPassword");
        return request;
    }
    
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}
