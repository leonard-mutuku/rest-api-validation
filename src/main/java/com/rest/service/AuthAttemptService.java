/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.service;

import org.springframework.stereotype.Service;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.rest.security.AuthenticationFailureEvent;
import com.rest.utils.Constants;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.context.event.EventListener;

/**
 *
 * @author leonard
 */
@Service
public class AuthAttemptService {
    
    private final LoadingCache<String, Integer> attemptsCache;

    public AuthAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(Constants.LOGIN_ATTEMPT_LOCK_DURATION, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    public void authFailed(final String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }

        attempts++;
        attemptsCache.put(key, attempts);
    }
    
    public boolean isBlocked(final String key) {
        try {
            return attemptsCache.get(key) >= Constants.MAX_LOGIN_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
    
    @EventListener
    public void onAuthenticationFailureEvent(final AuthenticationFailureEvent e) {
        authFailed(e.getIpAddress());
    }

}
