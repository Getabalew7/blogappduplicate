package com.codeaz.blogapp.users;

import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import com.codeaz.blogapp.users.dto.CreateUserRequestDTO;
import com.codeaz.blogapp.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO userRequest)
    {
        var user = userService.createUser(userRequest);
        return ResponseEntity.ok(modelMapper.map(user, UserResponseDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUSerById(@PathVariable Long id) throws UserNotFoundException {
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getUserByUsername(@RequestParam(required = false) String username) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserByUserName(username));
    }
}
