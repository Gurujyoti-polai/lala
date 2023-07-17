package com.jwt.example.services;

import com.jwt.example.entities.Address;
import com.jwt.example.entities.User;
import com.jwt.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
//        User savedUser = userRepository.save(user);
//        Address address = new Address();
//        addressService.saveAddress(savedUser.getId(),address);
//        return savedUser;
    }
}
