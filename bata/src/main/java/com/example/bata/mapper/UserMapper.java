package com.example.bata.mapper;


import com.example.bata.dtos.user.UserRequestDto;
import com.example.bata.dtos.user.UserResponseDto;
import com.example.bata.model.User;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto){
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        return user;
    }

    public UserResponseDto toResponseDto(User user){
      return new UserResponseDto(
              user.getId(),
              user.getFirstName(),
              user.getEmail(),
              user.getFirstName(),
              user.getPhoneNumber(),
              user.getRoles()
      );

    }
}
