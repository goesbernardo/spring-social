package com.bernardo.springsocial.controller;

import com.bernardo.springsocial.exception.ResourceNotFoundException;
import com.bernardo.springsocial.model.User;
import com.bernardo.springsocial.repository.UserRepository;
import com.bernardo.springsocial.security.CurrentUser;
import com.bernardo.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/google")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        return userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
