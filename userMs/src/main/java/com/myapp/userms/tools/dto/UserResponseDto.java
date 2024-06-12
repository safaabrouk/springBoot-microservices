package com.myapp.userms.tools.dto;

import com.myapp.userms.tools.extrenal.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private Address address;
}
