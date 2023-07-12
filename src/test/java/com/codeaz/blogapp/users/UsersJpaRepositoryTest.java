package com.codeaz.blogapp.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UsersJpaRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    @Order(1)
    void can_create_users() throws Exception {
        var userEntity = UserEntity.builder().userName("getch")
                .email("getch@gmail.com")
                .build();
        var user = userRepository.save(userEntity);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getUserName(), user.getUserName());
    }
    @Test
    @Order(2)
    void can_find_users() throws Exception {
        var user = UserEntity.builder().userName("getch")
                .email("getch@gmail.com")
                .build();
        userRepository.save(user);
        var oldUser = userRepository.findAll();
        assertNotNull(oldUser);
        assertEquals(oldUser.size(),1);
    }
    @Test
    public void can_find_user_by_id() throws Exception {
        var user = UserEntity.builder().userName("getch")
               .email("getch@gmail.com")
               .build();
        userRepository.save(user);
        var oldUser = userRepository.findById(user.getId());
        assertNotNull(oldUser);
        assertEquals(oldUser.get().getId(), user.getId());
    }
    @Test
    public void can_find_user_by_name() throws Exception {
        var user = UserEntity.builder().userName("getch")
              .email("getch@gmail.com")
              .build();
        userRepository.save(user);
        var oldUser = userRepository.findByUserName(user.getUserName());
        assertNotNull(oldUser);
        assertEquals(oldUser.get(0).getId(), user.getId());
    }
    @Test
    public void can_update_user() throws Exception {
        var user = UserEntity.builder().userName("getch")
              .email("getch@gmail.com")
              .build();
        userRepository.save(user);
        var oldUser = userRepository.findByUserName(user.getUserName());
        assertNotNull(oldUser);
        assertEquals(oldUser.get(0).getId(), user.getId());
        user.setEmail("updatedgech@gmail.com");
        userRepository.save(user);
        var newUser = userRepository.findByUserName(user.getUserName());
        assertNotNull(newUser);
        assertEquals(newUser.get(0).getId(), user.getId());
        assertEquals(newUser.get(0).getEmail(), user.getEmail());
    }
    @Test
    public void can_delete_user() throws Exception {
        var user = UserEntity.builder().userName("getch")
             .email("getch@gmail.com")
             .build();
        userRepository.save(user);
        var oldUser = userRepository.findById(user.getId());
        assertNotNull(oldUser);
        assertEquals(oldUser.get().getId(), user.getId());
        userRepository.delete(user);
        var newUser = userRepository.findById(user.getId());
        assertEquals(newUser.isEmpty(), true);
    }
}
