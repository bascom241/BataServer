package com.example.bata.service;


import com.example.bata.dao.UserPrincipal;
import com.example.bata.dao.UserRepository;
import com.example.bata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationEventListener {



    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event){
        String email = event.getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFailedLoggedInAttempts(0);
        user.setAccountNonLocked(true);
        user.setLockTime(null);
        userRepository.save(user);
    }

    @EventListener
    public void handleAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event){
        String email = (String) event.getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        int attempts = user.getFailedLoggedInAttempts() + 1;
        user.setFailedLoggedInAttempts(attempts);

        if(attempts > UserPrincipal.MAX_FAILED_ATTEMPTS){
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }


    }
}

