/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.security;

import com.rest.exception.AuthenticationException;
import com.rest.utils.Constants;
import com.rest.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author leonard
 */
public class RateLimitInterceptor implements HandlerInterceptor {
    
    private static final Map<String, Queue<Long>> requestMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = Utils.getClientIP(request);
        Queue<Long> requestQueue = requestMap.computeIfAbsent(ipAddress, k -> new ConcurrentLinkedQueue<>());

        long now = System.currentTimeMillis();
        requestQueue.add(now);

        while (!requestQueue.isEmpty() && now - requestQueue.peek() > Constants.RATE_TIME_WINDOW.toMillis()) {
            requestQueue.poll();
        }

        if (requestQueue.size() <= Constants.RATE_MAX_REQUESTS) {
            return true;
        } else {
            throw new AuthenticationException("Too many request. Rate limit exceeded!", 429);
        }
    }

}
