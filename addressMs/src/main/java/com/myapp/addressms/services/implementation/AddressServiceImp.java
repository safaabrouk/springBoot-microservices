package com.myapp.addressms.services.implementation;

import com.myapp.addressms.repositories.AddressRepository;
import com.myapp.addressms.services.AddresssService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddresssService {

    private AddressRepository addressRepository;
    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
