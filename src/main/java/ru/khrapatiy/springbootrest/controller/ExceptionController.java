package ru.khrapatiy.springbootrest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.khrapatiy.springbootrest.exception.InvalidCredentials;
import ru.khrapatiy.springbootrest.exception.UnauthorizedUser;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    private static final Logger myLogger = LogManager.getLogger(AuthorizationController.class);
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handlerInvalidCredentials(InvalidCredentials e) {
        myLogger.error("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handlerUnauthorizedUser(UnauthorizedUser e) {
        myLogger.error("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
