package com.codeaz.blogapp.users;

import com.codeaz.blogapp.users.Exception.UserNotFoundException;
import com.codeaz.blogapp.users.dto.CreateUserRequestDTO;
import com.codeaz.blogapp.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(CreateUserRequestDTO requestDTO) {
       var userEntity = modelMapper.map(requestDTO,UserEntity.class);
       userRepository.save(userEntity);
        return userEntity;
    }
    public UserEntity findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public UserResponseDTO getUserById(Long id) {
        var user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException(id);
        }
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
