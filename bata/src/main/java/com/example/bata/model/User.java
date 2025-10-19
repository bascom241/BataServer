package com.example.bata.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true ;
    private boolean credentialsNotExpired = true;
    private boolean enabled = true ;
    private LocalDateTime passwordLastChanged = LocalDateTime.now();
    private int failedLoggedInAttempts = 0;
    private LocalDateTime lockTime;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-productRatings")
    private List<ProductRating> productRatings;


}
