package com.example.bata.dtos.user;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Set<String> roles;
}
