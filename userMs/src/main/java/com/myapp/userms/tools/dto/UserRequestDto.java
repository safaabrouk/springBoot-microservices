package com.myapp.userms.tools.dto;

import com.myapp.userms.tools.extrenal.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private Address address;
}
