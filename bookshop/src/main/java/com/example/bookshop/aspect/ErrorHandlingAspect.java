package com.example.bookshop.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Aspect
@Component
@RestControllerAdvice
public class ErrorHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.bookshop..*(..))", throwing = "exception")
    public void logException(Exception exception) {
        logger.error("Exception occurred: ", exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        logger.error("Handled exception: ", exception);
        return ResponseEntity.status(500).body("An error occurred: " + exception.getMessage());
    }
}
