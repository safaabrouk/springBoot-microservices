package com.myapp.userms.services.implementation;

import com.myapp.userms.entities.User;
import com.myapp.userms.repositories.UserRepository;
import com.myapp.userms.services.UserService;
import com.myapp.userms.tools.dto.UserMapper;
import com.myapp.userms.tools.dto.UserRequestDto;
import com.myapp.userms.tools.dto.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().
                map(UserMapper::fromUserToUserResponseDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::fromUserToUserResponseDto).orElse(null);
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User createdUser = userRepository.save(UserMapper.fromUserRequestDtoToUser(userRequestDto));
        return UserMapper.fromUserToUserResponseDto(createdUser);
    }

    /*@Override
    public UserResponseDto getUserByEmail(String email) {
        return null;
    }*/

    @Override
    public UserResponseDto updateUser(int id, UserRequestDto userRequestDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            log.info("Updating user: " + updatedUser);
            updatedUser.setFirstName(userRequestDto.getFirstName());
            updatedUser.setLastName(userRequestDto.getLastName());
            updatedUser.setEmail(userRequestDto.getEmail());
            updatedUser.setPassword(userRequestDto.getPassword());
            updatedUser.setBirthDate(userRequestDto.getBirthDate());
            return UserMapper.fromUserToUserResponseDto( userRepository.save(updatedUser));
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}
