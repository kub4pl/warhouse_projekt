package com.example.warehouse.controller;

import com.example.warehouse.configuration.JwtUtils;
import com.example.warehouse.dto.PartDto;
import com.example.warehouse.dto.UserDto;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.RoleType;
import com.example.warehouse.model.User;
import com.example.warehouse.dto.UserDetailsDto;
import com.example.warehouse.model.Part;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    private static final String TOKEN_PREFIX = "Bearer";

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + " " + jwtUtils.generateJwtToken(authentication);
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setToken(jwt);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsDto);
    }
    @PostMapping("/login/save")
    public UserDto addLogin(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));


        Role role = new Role();
        role.setDescription(role.getDescription());
        role.setType(RoleType.USER);

        if(!roleRepository.existsByType(RoleType.USER))
        {
            roleRepository.save(role);
        }
        Optional<Role> roleOptional = roleRepository.findByType(RoleType.USER);
        if (roleOptional.isPresent()){
            role = roleOptional.get();
        }else {
            throw new RuntimeException(" Can not found role");
        }
        user.setRole(role);
        if(!userRepository.existsByUsername(user.getUsername())){

            userRepository.save(user);

        }
        return userDto;
    }




    @GetMapping("/auth/user")
    public ResponseEntity<?> getauthenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.status(HttpStatus.OK).body(authentication.getName());

    }

    @GetMapping("/auth/role")
    public ResponseEntity<?> roleauthenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.status(HttpStatus.OK).body(authentication.getAuthorities());

    }

}
