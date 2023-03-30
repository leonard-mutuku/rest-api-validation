/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rest.exception;

import com.rest.model.CustomErrorResp;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author leonard
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        CustomErrorResp apiError = new CustomErrorResp(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), ex.getMessage());
        return buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        CustomErrorResp apiError = new CustomErrorResp(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
        return buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAthentication(AuthenticationException ex) {
        HttpStatus status = ex.getCode() == null ? HttpStatus.UNAUTHORIZED : parseStatus(ex.getCode());
        CustomErrorResp apiError = new CustomErrorResp(status, LocalDateTime.now(), ex.getMessage());      
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
        CustomErrorResp apiError = new CustomErrorResp(HttpStatus.BAD_REQUEST, LocalDateTime.now(), errors);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(CustomErrorResp apiError) {
        return new ResponseEntity<>(apiError, apiError.status());
    }
    
    private HttpStatus parseStatus(Integer code) {
        return switch (code) {
            case 403 -> HttpStatus.FORBIDDEN;
            case 401 -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.NOT_IMPLEMENTED;
        };
    }

}
