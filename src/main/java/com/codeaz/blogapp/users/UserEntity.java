package com.codeaz.blogapp.users;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String email;
    private String bio;
    private String img;
}
