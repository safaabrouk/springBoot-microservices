package com.myapp.addressms.controllers;

import com.myapp.addressms.entities.Address;
import com.myapp.addressms.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Get all addresses :
    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAll() {
        return new ResponseEntity<>(addressService.getAllAddresses() , HttpStatus.OK);
    }

}
