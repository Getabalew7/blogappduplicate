package com.codeaz.blogapp.comments;

import com.codeaz.blogapp.articles.ArticleEntity;
import com.codeaz.blogapp.users.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "comments")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String body;
    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name="articleId", nullable = false)
    private ArticleEntity article;

    @CreatedDate
    private Date createdAt;

}
