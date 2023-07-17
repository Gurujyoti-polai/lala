package com.jwt.example.controller;

import com.jwt.example.entities.Address;
import com.jwt.example.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public List<Address> getUserAddresses(@PathVariable Long userId, Principal principal) {
        // Check if the logged-in user matches the requested userId
        if (principal.getName().equals(userId.toString())) {
            return addressService.getUserAddresses(userId);
        }
        throw new AccessDeniedException("Access denied");
    }

    @PostMapping("/{userId}")
    public Address saveUserAddress(@PathVariable Long userId, @RequestBody Address address, Principal principal) {
        // Check if the logged-in user matches the requested userId
        if (principal.getName().equals(userId.toString())) {
            return addressService.saveAddress(userId, address);
        }
        throw new AccessDeniedException("Access denied");
    }
}
