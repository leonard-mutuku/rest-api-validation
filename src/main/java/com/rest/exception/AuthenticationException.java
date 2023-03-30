/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.exception;

/**
 *
 * @author leonard
 */
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 6952607006172853453L;
    private Integer code;
    
    public AuthenticationException(String message) {
        super(message);
    }
    
    public AuthenticationException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    
    public Integer getCode() {
        return code;
    }
    
}
