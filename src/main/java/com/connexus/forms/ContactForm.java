package com.connexus.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {

    @NotBlank(message = "Name is required!")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long!")
    private String name;

    @NotBlank(message = "Phone number is required!")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits!")
    private String phoneNumber;

    @NotBlank(message = "Email is required!")
    @Email(message = "Please enter a valid email address!")
    private String email;

    @Size(max = 255, message = "Address must be less than 255 characters!")
    private String address;

    @Size(max = 1000, message = "Description must be less than 1000 characters!")
    private String description;

    private boolean favorite = false;

    @Pattern(regexp = "^(|https?://(www\\.)?[a-zA-Z0-9.-]+\\.[a-z]{2,}.*)$", message = "Please enter a valid website URL!")
    private String websiteLink;

    @Pattern(regexp = "^(|https?://(www\\.)?linkedin\\.com/.*)$", message = "Please enter a valid LinkedIn profile URL!")
    private String linkedInLink;

    // todo: Create a custom annotation for image file type and size validation
    // size
    // resolution
    private MultipartFile contactImage;
}
