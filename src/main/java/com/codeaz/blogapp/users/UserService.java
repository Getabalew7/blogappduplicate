package com.codeaz.blogapp.users;

import com.codeaz.blogapp.security.JwtService;
import com.codeaz.blogapp.users.Exception.InvalidCredentialException;
import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import com.codeaz.blogapp.users.dto.CreateUserRequestDTO;
import com.codeaz.blogapp.users.dto.UserResponse;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserEntity createUser(CreateUserRequestDTO requestDTO) {
       var userEntity = modelMapper.map(requestDTO,UserEntity.class);
       userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
       userRepository.save(userEntity);
        return userEntity;
    }
    public List<UserResponse> findUserByUserName(String userName){
        var users = userRepository.findByUserName(userName);
        if(users.isEmpty())
            throw new UserNotFoundException(userName);
        return users.stream().map(user->modelMapper.map(user,UserResponse.class)).collect(Collectors.toList());
    }
    public UserResponse getUserById(Long id) {
        //var userEntity = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        //var user= modelMapper.map(userEntity, UserResponse.class);
        // user.setToken(jwtService.generateToken(userEntity.getId()));
        UserResponse user = new UserResponse();
        user.setId(id);
        user.setUserName("gech");
        user.setEmail("test@test.com");

        return user;
    }
    public UserResponse loggedinUSer(String userName, String password){
        var user = userRepository.findByUserName(userName);
        if(user.size()==0){
            throw new UserNotFoundException(userName);
        }
        if(!passwordEncoder.matches(password,user.get(0).getPassword())){
            throw new InvalidCredentialException();
        }
        return modelMapper.map(user, UserResponse.class);
    }
}
