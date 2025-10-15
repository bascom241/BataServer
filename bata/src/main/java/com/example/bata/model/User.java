package com.example.bata.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // Personal details
    private String firstName;
    private String lastName;
    private String phoneNumber;

    // Security Questions
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER )
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles ;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean credentialsNotExpired;
    private boolean enabled;
    private LocalDateTime passwordLastChanged = LocalDateTime.now();
    private int failedLoggedInAttempts = 0;
    private LocalDateTime lockTime;


}
