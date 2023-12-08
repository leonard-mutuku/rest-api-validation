/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.controller;

import com.rest.exception.AuthenticationException;
import com.rest.model.User;
import com.rest.service.AuthAttemptService;
import com.rest.service.AuthService;
import com.rest.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
public class AuthController {
    
    @Autowired
    private AuthService service;
    
    @Autowired
    private AuthAttemptService authAttemptService;
    
    @PostMapping(path = "/authenticate")
    public ResponseEntity authenticateUser(@RequestBody User user, HttpServletRequest request) {
        String ip_address = Utils.getClientIP(request);
        if (authAttemptService.isBlocked(ip_address)) {
            throw new AuthenticationException("Login attempt blocked!. please try again later.", 403);
        }
        String response = service.Authenticate(user.username(), user.password(), ip_address);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
