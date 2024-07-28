package com.sm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Username is required")
    @Size(min=3,message="minimum 3 characters are required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min=6, message = "minimun 6 characters are required")
    private String password;
    @NotBlank(message = "please write something")
    private String about;
    @NotBlank(message = "please write your number")
    @Size(min = 8,max = 12,message = "Invalid number")
    private String phoneNumber;

 
}
