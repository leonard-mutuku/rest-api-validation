/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.controller;

import com.rest.model.User;
import com.rest.service.AuthService;
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
    
    @PostMapping(path = "/authenticate")
    public ResponseEntity authenticateUser(@RequestBody User user) {
        String response = service.Authenticate(user.username(), user.password());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
