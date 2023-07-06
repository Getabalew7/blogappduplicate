package com.codeaz.blogapp.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequestDTO {
    @NotNull
    private String userName;
    @NotNull
    private String email;
    private String password;
    private String bio;
    private  String img;
}
