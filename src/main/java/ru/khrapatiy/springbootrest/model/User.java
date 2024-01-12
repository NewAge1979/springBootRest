package ru.khrapatiy.springbootrest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    @NotBlank
    @Size(min = 3, max = 16, message = "Длина логин от 3 до 16 символов")
    private final String user;
    @NotBlank
    @Size(min = 9, message = "Длина пароля не меньше 9 символов")
    private String password;
    private List<Authorities> rights;
}
