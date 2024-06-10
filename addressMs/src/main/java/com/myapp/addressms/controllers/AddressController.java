package com.myapp.addressms.controllers;

import com.myapp.addressms.entities.Address;
import com.myapp.addressms.services.AddressService;
import com.myapp.addressms.tools.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // Get address by id :
    @GetMapping("/{id}")
    public ResponseEntity<Object>  getOne(@PathVariable int id) {
       Optional<Address> address = addressService.getAddressById(id);
        if(address.isPresent()) {
            return new ResponseEntity<>(address , HttpStatus.OK);
        }
        return new ResponseEntity<>("Address not found !" , HttpStatus.NOT_FOUND);
    }

    // Add new address :
    @PostMapping
    public ResponseEntity<Object>  add(@RequestBody AddressDto addressDto) {
        Address address = addressService.addAddress(addressDto);
        if(address != null) {
            return new ResponseEntity<>(address , HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Address not added" , HttpStatus.BAD_REQUEST);
    }


    // update address :
    @PutMapping("/{id}")
    public ResponseEntity<Object>  update(@PathVariable int id, @RequestBody AddressDto addressDto) {
        Address address = addressService.updateAddress(id,addressDto);
        if(address != null) {
            return new ResponseEntity<>(address , HttpStatus.OK);
        }
        return new ResponseEntity<>("Address not updated" , HttpStatus.NOT_FOUND);
    }



}
