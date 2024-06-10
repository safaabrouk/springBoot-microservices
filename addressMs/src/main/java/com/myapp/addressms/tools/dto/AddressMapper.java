package com.myapp.addressms.tools.dto;

import com.myapp.addressms.entities.Address;
import org.springframework.beans.BeanUtils;

public class AddressMapper {

    public static Address fromAddressDtoToAddress(AddressDto addressDto){
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return address;
    }

}
