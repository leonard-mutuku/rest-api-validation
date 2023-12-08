/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.utils;

import java.time.Duration;

/**
 *
 * @author leonard
 */
public class Constants {
    
    public static final int RATE_MAX_REQUESTS = 300;
    public static final Duration RATE_TIME_WINDOW = Duration.ofMinutes(2);
    
    public static final int MAX_LOGIN_ATTEMPT = 5;
    public static final int LOGIN_ATTEMPT_LOCK_DURATION = 15;
    
}
