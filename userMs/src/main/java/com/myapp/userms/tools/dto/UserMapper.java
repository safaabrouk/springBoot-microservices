package com.myapp.userms.tools.dto;

import com.myapp.userms.entities.User;
import com.myapp.userms.tools.extrenal.Address;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User fromUserRequestDtoToUser(UserRequestDto userRequestDto ){
        /*
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return user;
         */
        return new User().builder().
                firstName(userRequestDto.getFirstName()).
                lastName(userRequestDto.getLastName()).
                email(userRequestDto.getEmail()).
                password(userRequestDto.getPassword()).
                birthDate(userRequestDto.getBirthDate()).
                idAddress(userRequestDto.getAddress().getId()).build();

    }

    public static UserResponseDto fromUserToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }

    public static UserResponseDto fromUserAddressToResponseDto(User user, Address address){
        return new UserResponseDto().builder().
                id(user.getId()).
                firstName(user.getFirstName()).
                lastName(user.getLastName()).
                email(user.getEmail()).
                birthDate(user.getBirthDate()).
                address(address).
                build();
    }
}
