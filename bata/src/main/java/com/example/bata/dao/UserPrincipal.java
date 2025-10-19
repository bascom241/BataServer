package com.example.bata.dao;

import com.example.bata.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {

    private final User user;

    private static final int PASSWORD_EXPIRY_DAYS = 90;
    public static final int MAX_FAILED_ATTEMPTS=5;
    private static final int LOCK_TIME_IN_MINUTES = 5;


    public UserPrincipal(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(r-> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        if(!user.isAccountNonLocked()) return false;

        if(user.getLockTime() != null && LocalDateTime.now().isAfter(user.getLockTime().plusMinutes(LOCK_TIME_IN_MINUTES))){
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedLoggedInAttempts(0);
            return true;
        }

        return user.getFailedLoggedInAttempts() < MAX_FAILED_ATTEMPTS;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }


}
