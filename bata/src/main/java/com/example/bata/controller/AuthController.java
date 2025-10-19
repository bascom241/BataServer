package com.example.bata.controller;

import com.example.bata.dao.UserRepository;
import com.example.bata.dtos.user.UserRequestDto;
import com.example.bata.dtos.user.UserResponseDto;
import com.example.bata.mapper.UserMapper;
import com.example.bata.model.User;
import com.example.bata.service.JwtService;
import com.example.bata.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userDetailsServiceImplementation.registerUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto userRequestDto) {
        try {
            // Validate input
            if (userRequestDto.getEmail() == null || userRequestDto.getEmail().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
            }

            // Check if user exists
            User existingUser = userRepository.findByEmail(userRequestDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Check account lock status
            if (!existingUser.isAccountNonLocked() && existingUser.getFailedLoggedInAttempts() >= 5) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Account is locked. Please contact administrator.");
            }

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDto.getEmail(),
                            userRequestDto.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                // Reset failed attempts and generate token
                existingUser.setFailedLoggedInAttempts(0);
                existingUser.setLockTime(null);
                userRepository.save(existingUser);

                String token = jwtService.generateToken(userRequestDto.getEmail());

                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "message", "Login successful",
                        "user", Map.of(
                                "id", existingUser.getId(),
                                "email", existingUser.getEmail(),
                                "firstName", existingUser.getFirstName()
                        )
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }

        } catch (BadCredentialsException e) {
            handleFailedLogin(userRequestDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("User not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login failed: " + e.getMessage());
        }
    }

    private void handleFailedLogin(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                user.setFailedLoggedInAttempts(user.getFailedLoggedInAttempts() + 1);

                if (user.getFailedLoggedInAttempts() >= 5) {
                    user.setAccountNonLocked(false);
                    user.setLockTime(LocalDateTime.now());
                }
                userRepository.save(user);
            }
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
    }
}