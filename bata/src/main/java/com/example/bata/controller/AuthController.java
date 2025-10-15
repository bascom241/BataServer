package com.example.bata.controller;

import com.example.bata.dao.UserRepository;
import com.example.bata.dtos.user.UserRequestDto;
import com.example.bata.dtos.user.UserResponseDto;
import com.example.bata.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userRequestDto){
            UserResponseDto userResponseDto = userDetailsServiceImplementation.registerUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }
}
