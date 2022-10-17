package com.spring_boot.learning.expenses.Advices;

import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring_boot.learning.expenses.Exceptions.UserNotFoundException;
import com.spring_boot.learning.expenses.beans.ExceptionResponse;

@ControllerAdvice
@RestController
public class ResponseEntityExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAnyException(Exception ex, WebRequest req) {
        ExceptionResponse exr = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<Object>(exr, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest req) {
        ExceptionResponse exr = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<Object>(exr, HttpStatus.NOT_FOUND);
    }
    @Override  
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   
    {  
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), "Validation failed.", ex.getBindingResult().toString());  
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);  
    }  
}