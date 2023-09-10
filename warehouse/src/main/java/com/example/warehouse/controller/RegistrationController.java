package com.example.warehouse.controller;


import com.example.warehouse.dto.PasswordDto;
import com.example.warehouse.dto.UserDto;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.RoleType;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.EmailService;
import com.example.warehouse.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody String email) throws MessagingException, IOException {
        String token = UUID.randomUUID().toString();
        emailService.sendSimpleMessage(email, "Welcome Register!", "Register Now: http://localhost:8080/api/register/new/password/" + token);
        User user = new User();

        if (userRepository.existsByUsername(email)) {
            throw new RuntimeException(" Account exist in database ");
        }

        user.setUsername(email);
        user.setToken(token);

        Role role = new Role();
        role.setDescription(role.getDescription());
        role.setType(RoleType.USER);

        Optional<Role> roleOptional = roleRepository.findByType(RoleType.USER);
        if (roleOptional.isPresent()){
            role = roleOptional.get();
        }else {
            throw new RuntimeException(" Can not found role");
        }
        user.setRole(role);
        userRepository.save(user);
        return null;
    }

    @PutMapping("/register/new/password/{token}")
    public UserDto editRegisterUser(@PathVariable String token, @RequestBody PasswordDto passwordDto) {
        User user;
        Optional<User> tokenOptional = userRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            user = tokenOptional.get();
        } else {
            throw new RuntimeException(" Can not found token");
        }
        if (!PasswordValidator.isValidPassword(passwordDto.getPassword())) {
            throw new RuntimeException(" Password is not match ");
        }

        if (!passwordDto.getPassword().equals(passwordDto.getConfirmPassword())){

            throw new RuntimeException(" Password is not match ");

        }


        user.setPassword(bCryptPasswordEncoder.encode(passwordDto.getPassword()));

        userRepository.save(user);

        return null;

    }
}
