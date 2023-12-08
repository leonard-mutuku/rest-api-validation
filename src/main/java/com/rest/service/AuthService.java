/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.service;

import com.rest.exception.AuthenticationException;
import com.rest.model.User;
import com.rest.security.AuthenticationFailureEvent;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 *
 * @author leonard
 */
@Service
public class AuthService {
    
    @Autowired
    private TestService service;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    public String Authenticate(String username, String password, String ip_address) {
        List<User> users = service.getUsers();
        Predicate<? super User> predicate = user -> user.username().equals(username) && user.password().equals(password);
        boolean auth = users.stream().anyMatch(predicate);
        if (auth) {
            //you can perform operations to create session or jwt for further aauthentication
            return "user authenticated.";
        } else {
            eventPublisher.publishEvent(new AuthenticationFailureEvent(this, ip_address));
            throw new AuthenticationException("user authentication failed!.");
        }
    }
    
}
