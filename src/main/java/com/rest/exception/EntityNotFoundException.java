/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.exception;

/**
 *
 * @author leonard
 */
public class EntityNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = -50051518150393168L;
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
}
