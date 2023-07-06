package com.codeaz.blogapp.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String userName;
    private String email;
    private String bio;
    private  String img;
}
