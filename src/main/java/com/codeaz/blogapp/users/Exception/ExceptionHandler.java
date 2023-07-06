package com.codeaz.blogapp.users.Exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionHandler {

    private String message;
}
