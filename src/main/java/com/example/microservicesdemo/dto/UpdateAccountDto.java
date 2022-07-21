package com.example.microservicesdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateAccountDto {


    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name must be alphabetic")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name must be alphabetic")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
}
