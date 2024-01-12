package ru.khrapatiy.springbootrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khrapatiy.springbootrest.exception.InvalidCredentials;
import ru.khrapatiy.springbootrest.exception.UnauthorizedUser;
import ru.khrapatiy.springbootrest.model.Authorities;
import ru.khrapatiy.springbootrest.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean isEmpty(List<?> value) {
        return value == null || value.isEmpty();
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty.");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user: " + user);
        }
        return userAuthorities;
    }
}