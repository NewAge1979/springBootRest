package ru.khrapatiy.springbootrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private final String user;
    private String password;
    private List<Authorities> rights;
}
