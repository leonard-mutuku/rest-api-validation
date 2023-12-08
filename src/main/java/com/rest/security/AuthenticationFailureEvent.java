/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.security;

import org.springframework.context.ApplicationEvent;

/**
 *
 * @author leonard
 */
public class AuthenticationFailureEvent extends ApplicationEvent {

    private static final long serialVersionUID = -3301541216348895093L;
    
    private final String ipAddress;
    
    public AuthenticationFailureEvent(Object source, String ipAddress) {
        super(source);
        this.ipAddress = ipAddress;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
}
