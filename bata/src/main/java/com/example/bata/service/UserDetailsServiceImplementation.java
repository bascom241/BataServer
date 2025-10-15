package com.example.bata.service;

import com.example.bata.dao.UserPrincipal;
import com.example.bata.dao.UserRepository;
import com.example.bata.dtos.user.UserRequestDto;
import com.example.bata.dtos.user.UserResponseDto;
import com.example.bata.exception.UserAlreadyExistsException;
import com.example.bata.mapper.UserMapper;
import com.example.bata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("user not found"));
        return new UserPrincipal(user);
    }


    public UserResponseDto registerUser(UserRequestDto userRequestDto){
        Optional<User> existingUser = userRepository.findByEmail(userRequestDto.getEmail());

        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("user already exits with this email");
        }


        // Convert requestDto into an entity
        User newUser = userMapper.toEntity(userRequestDto);

        newUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        newUser.setRoles(Set.of("USER"));

        User savedUser = userRepository.save(newUser);

        //convert the user entity into a response Object
        return userMapper.toResponseDto(savedUser);
    }






}
