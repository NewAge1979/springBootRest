package ru.khrapatiy.springbootrest.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    @NotBlank(message = "User name is empty.")
    //@Size(min = 3, max = 16, message = "The length of the user name must be from 3 to 16 characters.")
    private final String user;
    @NotBlank(message = "Password is empty.")
    //@Size(min = 9, message = "The password must be at least 9 characters long.")
    private String password;
    private List<Authorities> rights;
}
