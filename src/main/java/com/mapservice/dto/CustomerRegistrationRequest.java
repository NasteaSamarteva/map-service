package com.mapservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationRequest {
   @NotBlank(message="username mustn't be blank")
    @NotNull(message="username mustn't be null")
    private String username;
    @NotBlank(message="password mustn't be blank")
    @NotNull(message="password mustn't be null")
    private String password;
    @NotBlank(message="email mustn't be blank")
    @NotNull(message="email mustn't be null")
    private String email;
}
