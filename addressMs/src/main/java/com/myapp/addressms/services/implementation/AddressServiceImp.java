package com.myapp.addressms.services.implementation;

import com.myapp.addressms.repositories.AddressRepository;
import com.myapp.addressms.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {

    private AddressRepository addressRepository;
    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
