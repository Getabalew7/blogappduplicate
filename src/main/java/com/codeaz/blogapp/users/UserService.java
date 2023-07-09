package com.codeaz.blogapp.users;

import com.codeaz.blogapp.users.Exception.InvalidCredentialException;
import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import com.codeaz.blogapp.users.dto.CreateUserRequestDTO;
import com.codeaz.blogapp.users.dto.UserResponse;
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

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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
        var user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        return modelMapper.map(user, UserResponse.class);
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
