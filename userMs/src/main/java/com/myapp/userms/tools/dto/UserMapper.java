package com.myapp.userms.tools.dto;

import com.myapp.userms.entities.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User fromUserRequestDtoToUser(UserRequestDto userRequestDto ){
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return user;
    }

    public static UserResponseDto fromUserToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }
}
