package com.myapp.userms.controllers;

import com.myapp.userms.services.UserService;
import com.myapp.userms.tools.dto.UserRequestDto;
import com.myapp.userms.tools.dto.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        if(userResponseDto != null) {
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }

    // Create user
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Creating user: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        if(userResponseDto != null) {
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User creation failed",HttpStatus.BAD_REQUEST);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
        if(userResponseDto != null) {
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("User update failed",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        boolean isDeleted =  userService.deleteUser(id);
        if(isDeleted) {
            return new ResponseEntity<>("User deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }
}
