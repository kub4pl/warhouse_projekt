package com.example.warehouse.service;


import com.example.warehouse.configuration.UserDetailsImpl;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not Found with username: " + username));


        return UserDetailsImpl.build(user);
    }
}
