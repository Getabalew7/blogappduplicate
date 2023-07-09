package com.codeaz.blogapp.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private String email;
    private String bio;
    private  String img;
    private String token;
}
