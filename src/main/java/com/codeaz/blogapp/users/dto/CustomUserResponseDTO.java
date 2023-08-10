package com.codeaz.blogapp.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomUserResponseDTO  extends UserResponse{
    @JsonIgnore
    String email;
    @JsonIgnore
     String img;
    @JsonIgnore
     String token;
    @JsonIgnore
    String bio;
}
