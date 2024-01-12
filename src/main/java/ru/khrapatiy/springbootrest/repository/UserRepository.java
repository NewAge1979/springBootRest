package ru.khrapatiy.springbootrest.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.khrapatiy.springbootrest.model.Authorities;
import ru.khrapatiy.springbootrest.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final List<User> users;
    private static final Logger myLogger = LogManager.getLogger(UserRepository.class);

    public UserRepository() {
        users = List.of(
                new User("user1", "password1", List.of(Authorities.READ)),
                new User("user2", "password2", List.of(Authorities.READ, Authorities.WRITE)),
                new User("user3", "password3", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE)),
                new User("user", "password", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE))
        );
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        myLogger.info(String.format("Parameters: user = %s; password = %s.", user, password));
        Optional<User> currentUser = users.stream()
                .filter(x -> x.getUser().equals(user) && x.getPassword().equals(password))
                .findFirst();
        return currentUser.map(User::getRights).orElse(null);
    }
}