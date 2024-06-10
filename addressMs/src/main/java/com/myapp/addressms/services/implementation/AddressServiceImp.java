package com.myapp.addressms.services.implementation;

import com.myapp.addressms.entities.Address;
import com.myapp.addressms.repositories.AddressRepository;
import com.myapp.addressms.services.AddressService;
import com.myapp.addressms.tools.dto.AddressDto;
import com.myapp.addressms.tools.dto.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService {

    private AddressRepository addressRepository;
    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address addAddress(AddressDto addressDto) {
        return addressRepository.save(AddressMapper.fromAddressDtoToAddress(addressDto));
    }

    @Override
    public Address updateAddress(int id, AddressDto addressDto) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            Address updatedAddress = address.get();
            updatedAddress.setCity(addressDto.getCity());
            updatedAddress.setCountry(addressDto.getCountry());
            updatedAddress.setStreet(addressDto.getStreet());
            return addressRepository.save(updatedAddress);
        }
        return null;
    }

    @Override
    public boolean deleteAddress(int id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.delete(address.get());
            return true;
        }
        return false;
    }
}
