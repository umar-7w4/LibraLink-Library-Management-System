package com.libralink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImplementation implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
            return authentication.isAuthenticated();
        } catch (Exception e) {
            return false;
        }
    }
}
