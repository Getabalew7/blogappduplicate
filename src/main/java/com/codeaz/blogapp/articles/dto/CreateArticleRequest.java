package com.codeaz.blogapp.articles.dto;

import com.codeaz.blogapp.users.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
public class CreateArticleRequest {
    @Column(nullable = false)
    @NotNull
    private String title;
    @Column(unique = true)
    private String subtitle;
    @Column(nullable = false)
    @NotNull
    private String body;

}
