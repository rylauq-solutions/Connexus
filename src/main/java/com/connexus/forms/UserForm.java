package com.connexus.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    @NotBlank(message = "Name is required!")
    @Size(min = 3, message = "Name must be at least 3 characters long!")
    private String name;

    @NotBlank(message = "Email is required!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "Password must be at least 6 characters long!")
    private String password;
    
    // @NotBlank(message = "About is required!")
    private String about;

    @Size(min = 8, max = 12, message = "Phone number must be between 8 to 12 characters long!")
    private String phoneNumber;
}
