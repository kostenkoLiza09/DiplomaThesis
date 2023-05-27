package ua.com.sweet.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import ua.com.sweet.models.Position;

public class UserDto {

    private Long id;

    @NotEmpty(message = "Введіть ім'я")
    private String firstName;

    @NotEmpty(message = "Введіть прізвище")
    private String lastName;

    @NotEmpty(message = "Введіть електронну пошту")
    @Email
    private String email;

    @NotEmpty(message = "Введіть пароль")
    private String password;

    @Enumerated(EnumType.STRING)
    private Position position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = Position.valueOf(position);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto(Long id, String firstName, String lastName, String email, String password, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.position = Position.valueOf(position);
        ;
    }

    public UserDto() {
    }
}