package ru.khrapatiy.springbootrest.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.khrapatiy.springbootrest.exception.InvalidCredentials;
import ru.khrapatiy.springbootrest.exception.UnauthorizedUser;
import ru.khrapatiy.springbootrest.model.Authorities;
import ru.khrapatiy.springbootrest.service.AuthorizationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private static final Logger myLogger = LogManager.getLogger(AuthorizationController.class);

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return authorizationService.getAuthorities(user, password);
    }

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
}
