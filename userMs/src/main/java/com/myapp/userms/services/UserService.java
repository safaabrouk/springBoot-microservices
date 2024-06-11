package com.myapp.userms.services;

import com.myapp.userms.tools.dto.UserRequestDto;
import com.myapp.userms.tools.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto getUserById(int id);
    // public UserResponseDto getUserByEmail(String email);
    public UserResponseDto updateUser(int id ,UserRequestDto userRequestDto);
    public boolean deleteUser(int id);
}
