/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.model;

import com.fasterxml.jackson.annotation.JsonFilter;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author leonard
 */
@JsonFilter("userFilter")
//@Entity(name = "user_info")
public record User(
//        @Id
//        @GeneratedValue
        Integer id,
        @NotBlank(message = "username is required")
        String username,
        @NotEmpty(message = "password is required")
        String password,
        @NotBlank(message = "email is required")
        @Email(message = "invalid email address")
        String email) {

}
