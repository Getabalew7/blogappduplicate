package com.codeaz.blogapp.users;

import com.codeaz.blogapp.security.JwtService;
import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import com.codeaz.blogapp.users.dto.CreateUserRequestDTO;
import com.codeaz.blogapp.users.dto.UserLoginDTO;
import com.codeaz.blogapp.users.dto.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserController(UserService userService, ModelMapper modelMapper, JwtService jwtService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUSerById(@PathVariable Long id) throws UserNotFoundException {
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getUserByUsername(@RequestParam(required = false) String username)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserByUserName(username));
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequestDTO userRequest)
    {
        var user = userService.createUser(userRequest);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setToken(jwtService.generateToken(user.getId()));
        return ResponseEntity.ok(userResponse);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginDTO userLogin){
        var user = userService.loggedinUSer(userLogin.getUserName(), userLogin.getPassword());
        user.setToken(jwtService.generateToken(user.getId()));
        return ResponseEntity.ok(user);
    }
}
