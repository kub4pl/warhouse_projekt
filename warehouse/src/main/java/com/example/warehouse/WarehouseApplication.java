package com.example.warehouse;

import com.example.warehouse.model.Role;
import com.example.warehouse.model.RoleType;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.Optional;
@EnableFeignClients
@EnableWebMvc
@SpringBootApplication
public class WarehouseApplication {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder bCryptPasswordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@PostConstruct
	public void init()
	{
		User user = new User();
		user.setUsername("Kuba200");
		user.setPassword(bCryptPasswordEncoder.encode("123456"));

		Role role = new Role();
		role.setDescription("USER");
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
	}

}
