package com.codeaz.blogapp.users.Exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with username : " + username + " Not found");
    }
    public UserNotFoundException(Long id){
        super("User with id : " + id + " is not found");
    }
}
