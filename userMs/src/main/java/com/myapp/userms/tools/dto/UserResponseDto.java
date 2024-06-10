package com.myapp.userms.tools.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
}
