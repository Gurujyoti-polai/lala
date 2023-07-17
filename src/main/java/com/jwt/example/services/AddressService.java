package com.jwt.example.services;

import com.jwt.example.entities.Address;
import com.jwt.example.entities.User;
import com.jwt.example.repositories.AddressRepository;
import com.jwt.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getUserAddresses(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getAddresses();
        }
        throw new RuntimeException("User not found");
    }

    public Address saveAddress(Long userId, Address address) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            address.setUser(user);
            return addressRepository.save(address);
        }
        throw new RuntimeException("User not found");
    }
}
