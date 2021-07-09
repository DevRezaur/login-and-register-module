package com.devrezaur.main.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.devrezaur.main.model.Role;
import com.devrezaur.main.model.User;
import com.devrezaur.main.repository.RoleRepository;
import com.devrezaur.main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findUserByUsername(String username) {
		return userRepository.findByUsernameIgnoreCase(username.toLowerCase());
	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("ROLE_USER");
		user.setRoles(Arrays.asList(userRole));
		
        return user = userRepository.save(user);
	}
	
	public User saveAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
		user.setRoles(Arrays.asList(adminRole));
		
        return user = userRepository.save(user);
	}
	
}
