package com.libralink.service;

import com.libralink.entity.User;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void save(User user);
    Optional<User> findByUsername(String username);
    UserDetails getCurrentUser();
}
