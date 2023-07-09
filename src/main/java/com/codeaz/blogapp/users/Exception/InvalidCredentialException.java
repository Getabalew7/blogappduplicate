package com.codeaz.blogapp.users.Exception;

import lombok.*;
public class InvalidCredentialException extends IllegalArgumentException {
public InvalidCredentialException(){
    super("Either user or password is invalid");
    }
}
