package com.myapp.addressms.services;

import com.myapp.addressms.entities.Address;
import com.myapp.addressms.tools.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    public Address addAddress(AddressDto addressDto);
    public List<Address> getAllAddresses();
    public Optional<Address> getAddressById(int id);
    public Address updateAddress(int id, AddressDto addressDto);
    public boolean deleteAddress(int id);

}
