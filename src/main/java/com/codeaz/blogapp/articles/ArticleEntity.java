package com.codeaz.blogapp.articles;

import com.codeaz.blogapp.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "articles")
@Setter
@Getter
@NoArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(unique = true)
    private String slug;
    private String subtitle;
    @Column(nullable = false)
    @NotNull
    private String body;
    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    private UserEntity author;
    @CreatedDate
    private Date createdAt;

}
