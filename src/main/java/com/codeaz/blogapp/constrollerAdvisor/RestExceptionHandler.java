package com.codeaz.blogapp.constrollerAdvisor;

import com.codeaz.blogapp.articles.Exception.ArticleNotFoundException;
import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class, ArticleNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.NOT_FOUND.toString().toLowerCase());
        body.put("message", ex.getMessage());


        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}

