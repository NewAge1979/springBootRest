package ru.khrapatiy.springbootrest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khrapatiy.springbootrest.model.Authorities;
import ru.khrapatiy.springbootrest.model.User;
import ru.khrapatiy.springbootrest.service.AuthorizationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User currentUser) {
        return authorizationService.getAuthorities(currentUser.getUser(), currentUser.getPassword());
    }
}
