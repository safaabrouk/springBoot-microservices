package com.myapp.addressms.controllers;

import com.myapp.addressms.entities.Address;
import com.myapp.addressms.services.AddressService;
import com.myapp.addressms.tools.dto.AddressDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
@Slf4j
public class AddressController {

    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Get all addresses :
    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAll() {
        log.info("Get all addresses");
        return new ResponseEntity<>(addressService.getAllAddresses() , HttpStatus.OK);
    }

    // Get address by id :
    @GetMapping("/{id}")
    public ResponseEntity<Object>  getOne(@PathVariable int id) {
       Optional<Address> address = addressService.getAddressById(id);
        if(address.isPresent()) {
            log.info("Get address with id {}", id);
            return new ResponseEntity<>(address , HttpStatus.OK);
        }
        return new ResponseEntity<>("Address not found !" , HttpStatus.NOT_FOUND);
    }

    // Add new address :
    @PostMapping
    public ResponseEntity<Object>  add(@RequestBody AddressDto addressDto) {
        Address address = addressService.addAddress(addressDto);
        if(address != null) {
            log.info("Add new address {} ",address);
            return new ResponseEntity<>(address , HttpStatus.CREATED);
        }
        log.error("Address not added");
        return new ResponseEntity<>("Address not added" , HttpStatus.BAD_REQUEST);
    }


    // update address :
    @PutMapping("/{id}")
    public ResponseEntity<Object>  update(@PathVariable int id, @RequestBody AddressDto addressDto) {
        Address address = addressService.updateAddress(id,addressDto);
        if(address != null) {
            log.info("Update address with id {}", id);
            return new ResponseEntity<>(address , HttpStatus.OK);
        }
        log.error("Address with id {} doesn't exist to update", id);
        return new ResponseEntity<>("Address not updated" , HttpStatus.NOT_FOUND);
    }

    // Delete address:
    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  update(@PathVariable int id) {
        boolean deleted = addressService.deleteAddress(id);
        if(deleted) {
            log.warn("Delete address with id {}", id);
            return new ResponseEntity<>("Address deleted successfully" , HttpStatus.OK);
        }
        log.error("Address with id {} doesn't exist to delete", id);
        return new ResponseEntity<>("Address not deleted" , HttpStatus.NOT_FOUND);
    }



}
