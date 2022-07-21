package com.example.microservicesdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateAccountDto {

    @NotBlank(message = "Login is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Login must be alphanumeric")
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
    private String login;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password must be alphanumeric")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name must be alphabetic")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name must be alphabetic")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
}
