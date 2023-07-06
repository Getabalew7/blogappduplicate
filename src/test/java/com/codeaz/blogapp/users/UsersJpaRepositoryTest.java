package com.codeaz.blogapp.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersJpaRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    @Order(1)
    void can_create_users() throws Exception {
        var user = UserEntity.builder().userName("getch")
                .email("getch@gmail.com")
                .build();
        userRepository.save(user);
    }
    @Test
    @Order(2)
    void can_find_users() throws Exception {
        var user = UserEntity.builder().userName("getch")
                .email("getch@gmail.com")
                .build();
        userRepository.save(user);
        var oldUser = userRepository.findAll();
        Assertions.assertEquals(oldUser.size(),1);
    }
}
