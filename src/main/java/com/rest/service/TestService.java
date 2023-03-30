/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.service;

import com.rest.exception.EntityNotFoundException;
import com.rest.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;

/**
 *
 * @author leonard
 */
@Service
public class TestService {
    
    private static Integer count = 1;
    private static final List<User> users = new ArrayList<>();
    
    public List<User> getUsers() {
        return users;
    }
    
    public User getUser(Integer id) {
        return users.stream().filter(u -> u.id().equals(id)).findFirst().orElseThrow(() -> new EntityNotFoundException("user not found"));
    }
    
    public User saveUser(User user) {
        User newUser = new User(count++, user.username(), user.password(), user.email());
        users.add(newUser);
        return newUser;
    }
    
    public void deleteUser(Integer id) {
        Predicate<? super User> predicate = user -> user.id().equals(id);
        boolean delete = users.removeIf(predicate);
        if (!delete) {
            throw new EntityNotFoundException("Delete failed!. user not found.");
        }
    }
    
}
