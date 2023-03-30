/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.model.User;
import com.rest.service.TestService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author leonard
 */
@RestController
public class TestController {
    
    @Autowired
    private TestService service;
    
    @GetMapping(path = "/users")
    public ResponseEntity getUsers() {
        List<User> users = service.getUsers();
        
        String[] strArray = {"password"};
        MappingJacksonValue mapping = mapResponse(users, strArray);
        
        return ResponseEntity.status(HttpStatus.OK).body(mapping);
    }
    
    @GetMapping(path = "/users/{user_id}")
    public ResponseEntity getUser(@PathVariable("user_id") Integer userId) {
        User user = service.getUser(userId);
        
        String[] strArray = {"id","password"};
        MappingJacksonValue mapping = mapResponse(user, strArray);
        
        return ResponseEntity.status(HttpStatus.OK).body(mapping);    
    }
    
    @PostMapping(path = "/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {
        User newUser = service.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.id())
                .toUri();
        return ResponseEntity.created(uri).body("user created successful.");
    }
    
    @DeleteMapping(path = "/users/{user_id}")
    public ResponseEntity deleteUser(@PathVariable("user_id") Integer userId) {
        service.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted successful.");    
    }
    
    private MappingJacksonValue mapResponse(Object dataObj, String[] strArray) {
        MappingJacksonValue mapping = new MappingJacksonValue(dataObj);        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(strArray);
        FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
        mapping.setFilters(filters);
        
        return mapping;
    }
    
}
